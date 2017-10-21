	@Override
	public void afterCompletion(boolean successful, boolean delayed) {
		if ( !transactionCoordinatorOwner.isActive() ) {
			return;
		}

		final int statusToSend =  successful ? Status.STATUS_COMMITTED : Status.STATUS_UNKNOWN;
		synchronizationRegistry.notifySynchronizationsAfterTransactionCompletion( statusToSend );

//		afterCompletionAction.doAction( this, statusToSend );

		transactionCoordinatorOwner.afterTransactionCompletion( successful, delayed );

		for ( TransactionObserver observer : observers() ) {
			observer.afterCompletion( successful, delayed );
		}

		if ( physicalTransactionDelegate != null ) {
			physicalTransactionDelegate.invalidate();
		}

		physicalTransactionDelegate = null;
		synchronizationRegistered = false;
	}
