	@Override
	public void begin() {
		try {
			if ( getStatus() == TransactionStatus.NOT_ACTIVE ) {
				log.trace( "Calling TransactionManager#begin" );
				transactionManager.begin();
				initiator = true;
				log.trace( "Called TransactionManager#begin" );
			}
			else {
				log.trace( "Skipping TransactionManager#begin due to already active transaction" );
			}
		}
		catch (Exception e) {
			throw new TransactionException( "JTA TransactionManager#begin failed", e );
		}
	}
