	private void invalidateSpaces(Serializable... spaces) {
		if ( spaces != null && spaces.length > 0 ) {
			for ( Serializable s : spaces ) {
				if( afterTransactionProcesses == null ) {
					afterTransactionProcesses = new AfterTransactionCompletionProcessQueue( session );
				}
				afterTransactionProcesses.addSpaceToInvalidate( (String) s );
			}
			// Performance win: If we are processing an ExecutableList, this will only be called once
			session.getFactory().getUpdateTimestampsCache().preInvalidate( spaces, session );
		}
	}
