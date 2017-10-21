	@Override
	public void explicitJoin() {
		if ( synchronizationRegistered ) {
			log.debug( "JTA transaction was already joined (RegisteredSynchronization already registered)" );
			return;
		}

		if ( getTransactionDriverControl().getStatus() != TransactionStatus.ACTIVE ) {
			throw new TransactionRequiredForJoinException(
					"Explicitly joining a JTA transaction requires a JTA transaction be currently active"
			);
		}

		joinJtaTransaction();
	}
