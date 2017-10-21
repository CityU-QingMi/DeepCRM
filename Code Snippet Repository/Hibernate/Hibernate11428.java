	private void invalidateAcrossCluster(boolean synchronous, Object[] keys, InvocationContext ctx) throws Throwable {
		// increment invalidations counter if statistics maintained
		incrementInvalidations();
		final InvalidateCommand invalidateCommand = commandsFactory.buildInvalidateCommand( InfinispanCollections.<Flag>emptySet(), keys );
		if ( log.isDebugEnabled() ) {
			log.debug( "Cache [" + rpcManager.getAddress() + "] replicating " + invalidateCommand );
		}

		ReplicableCommand command = invalidateCommand;
		if ( ctx.isInTxScope() ) {
			TxInvocationContext txCtx = (TxInvocationContext) ctx;
			// A Prepare command containing the invalidation command in its 'modifications' list is sent to the remote nodes
			// so that the invalidation is executed in the same transaction and locks can be acquired and released properly.
			// This is 1PC on purpose, as an optimisation, even if the current TX is 2PC.
			// If the cache uses 2PC it's possible that the remotes will commit the invalidation and the originator rolls back,
			// but this does not impact consistency and the speed benefit is worth it.
			command = commandsFactory.buildPrepareCommand( txCtx.getGlobalTransaction(), Collections.<WriteCommand>singletonList( invalidateCommand ), true );
		}
		rpcManager.invokeRemotely( getMembers(), command, synchronous ? syncRpcOptions : asyncRpcOptions );
	}
