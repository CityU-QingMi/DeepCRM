	@Override
	public void rollback() {
		try {
			log.trace( "Preparing to rollback transaction via JDBC Connection.rollback()" );
			getConnectionForTransactionManagement().rollback();
			status = TransactionStatus.ROLLED_BACK;
			log.trace( "Transaction rolled-back via JDBC Connection.rollback()" );
		}
		catch( SQLException e ) {
			throw new TransactionException( "Unable to rollback against JDBC Connection", e );
		}

		afterCompletion();
	}
