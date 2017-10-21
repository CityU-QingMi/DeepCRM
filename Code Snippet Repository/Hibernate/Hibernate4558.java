	@Override
	public void begin() {
		try {
			if ( !doConnectionsFromProviderHaveAutoCommitDisabled() ) {
				log.trace( "Preparing to begin transaction via JDBC Connection.setAutoCommit(false)" );
				getConnectionForTransactionManagement().setAutoCommit( false );
				log.trace( "Transaction begun via JDBC Connection.setAutoCommit(false)" );
			}
			status = TransactionStatus.ACTIVE;
		}
		catch( SQLException e ) {
			throw new TransactionException( "JDBC begin transaction failed: ", e );
		}
	}
