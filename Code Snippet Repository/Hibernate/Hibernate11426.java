	private Object handleInvalidate(InvocationContext ctx, WriteCommand command, Object... keys) throws Throwable {
		Object retval = invokeNextInterceptor( ctx, command );
		if ( command.isSuccessful() && !ctx.isInTxScope() ) {
			if ( keys != null && keys.length != 0 ) {
				if ( !isLocalModeForced( command ) ) {
					invalidateAcrossCluster( isSynchronous( command ), keys, ctx );
				}
			}
		}
		return retval;
	}
