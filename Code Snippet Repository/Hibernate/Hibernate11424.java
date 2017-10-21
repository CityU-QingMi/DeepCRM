	@Override
	public Object visitPrepareCommand(TxInvocationContext ctx, PrepareCommand command) throws Throwable {
		Object retval = invokeNextInterceptor( ctx, command );
		log.tracef( "Entering InvalidationInterceptor's prepare phase.  Ctx flags are empty" );
		// fetch the modifications before the transaction is committed (and thus removed from the txTable)
		if ( shouldInvokeRemoteTxCommand( ctx ) ) {
			if ( ctx.getTransaction() == null ) {
				throw new IllegalStateException( "We must have an associated transaction" );
			}

			List<WriteCommand> mods = Arrays.asList( command.getModifications() );
			broadcastInvalidateForPrepare( mods, ctx );
		}
		else {
			log.tracef( "Nothing to invalidate - no modifications in the transaction." );
		}
		return retval;
	}
