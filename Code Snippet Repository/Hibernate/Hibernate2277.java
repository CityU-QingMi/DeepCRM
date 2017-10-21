	private void registerCleanupActions(Executable executable) {
		if( executable.getBeforeTransactionCompletionProcess() != null ) {
			if( beforeTransactionProcesses == null ) {
				beforeTransactionProcesses = new BeforeTransactionCompletionProcessQueue( session );
			}
			beforeTransactionProcesses.register( executable.getBeforeTransactionCompletionProcess() );
		}
		if ( session.getFactory().getSessionFactoryOptions().isQueryCacheEnabled() ) {
			invalidateSpaces( executable.getPropertySpaces() );
		}
		if( executable.getAfterTransactionCompletionProcess() != null ) {
			if( afterTransactionProcesses == null ) {
				afterTransactionProcesses = new AfterTransactionCompletionProcessQueue( session );
			}
			afterTransactionProcesses.register( executable.getAfterTransactionCompletionProcess() );
		}
	}
