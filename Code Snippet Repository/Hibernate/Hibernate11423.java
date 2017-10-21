	@Override
	public Object visitClearCommand(InvocationContext ctx, ClearCommand command) throws Throwable {
		Object retval = invokeNextInterceptor( ctx, command );
		if ( !isLocalModeForced( command ) ) {
			// just broadcast the clear command - this is simplest!
			if ( ctx.isOriginLocal() ) {
				rpcManager.invokeRemotely( getMembers(), command, isSynchronous(command) ? syncRpcOptions : asyncRpcOptions );
			}
		}
		return retval;
	}
