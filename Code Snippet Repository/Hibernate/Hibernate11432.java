	@Override
	public Object visitPutKeyValueCommand(InvocationContext ctx, PutKeyValueCommand command) throws Throwable {
		if (command.hasFlag(Flag.CACHE_MODE_LOCAL)) {
			// for state-transfer related writes
			return invokeNextInterceptor(ctx, command);
		}
		int commandTopologyId = command.getTopologyId();
		int currentTopologyId = stateTransferManager.getCacheTopology().getTopologyId();
		if (commandTopologyId != -1 && currentTopologyId != commandTopologyId) {
			throw new OutdatedTopologyException("Cache topology changed while the command was executing: expected " +
				commandTopologyId + ", got " + currentTopologyId);
		}

		ConsistentHash writeCH = distributionManager.getWriteConsistentHash();
		List<Address> owners = null;
		if (writeCH.isReplicated()) {
			// local result is always ignored
			invokeNextInterceptor(ctx, command);
		}
		else {
			owners = writeCH.locateOwners(command.getKey());
			if (owners.contains(rpcManager.getAddress())) {
				invokeNextInterceptor(ctx, command);
			}
			else {
				log.tracef("Not invoking %s on %s since it is not an owner", command, rpcManager.getAddress());
			}
		}

		if (ctx.isOriginLocal() && command.isSuccessful()) {
			// This is called with the entry locked. In order to avoid deadlocks we must not wait for RPC while
			// holding the lock, therefore we'll return a future and wait for it in LockingInterceptor after
			// unlocking (and committing) the entry.
			return rpcManager.invokeRemotelyAsync(owners, command, isSynchronous(command) ? syncRpcOptions : asyncRpcOptions);
		}
		return null;
	}
