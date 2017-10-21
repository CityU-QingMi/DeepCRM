	private void afterCompletionCallback(boolean successful) {
		log.tracef( "ResourceLocalTransactionCoordinatorImpl#afterCompletionCallback(%s)", successful );
		final int statusToSend = successful ? Status.STATUS_COMMITTED : Status.STATUS_UNKNOWN;
		synchronizationRegistry.notifySynchronizationsAfterTransactionCompletion( statusToSend );

		transactionCoordinatorOwner.afterTransactionCompletion( successful, false );
		for ( TransactionObserver observer : observers() ) {
			observer.afterCompletion( successful, false );
		}
	}
