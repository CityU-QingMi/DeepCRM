	@Override
	public Object visitLockControlCommand(TxInvocationContext ctx, LockControlCommand command) throws Throwable {
		Object retVal = invokeNextInterceptor( ctx, command );
		if ( ctx.isOriginLocal() ) {
			//unlock will happen async as it is a best effort
			boolean sync = !command.isUnlock();
			List<Address> members = getMembers();
			( (LocalTxInvocationContext) ctx ).remoteLocksAcquired(members);
			rpcManager.invokeRemotely(members, command, sync ? syncRpcOptions : asyncRpcOptions );
		}
		return retVal;
	}
