	protected void resetConnection(boolean initiallyAutoCommit) {
		try {
			if ( initiallyAutoCommit ) {
				log.trace( "re-enabling auto-commit on JDBC Connection after completion of JDBC-based transaction" );
				getConnectionForTransactionManagement().setAutoCommit( true );
				status = TransactionStatus.NOT_ACTIVE;
			}
		}
		catch ( Exception e ) {
			log.debug(
					"Could not re-enable auto-commit on JDBC Connection after completion of JDBC-based transaction : " + e
			);
		}
	}
