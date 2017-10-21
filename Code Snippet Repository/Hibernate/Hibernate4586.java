	private void afterBeginCallback() {
		if(this.timeOut > 0) {
			transactionCoordinatorOwner.setTransactionTimeOut( this.timeOut );
		}
		transactionCoordinatorOwner.afterTransactionBegin();
		for ( TransactionObserver observer : observers() ) {
			observer.afterBegin();
		}
		log.trace( "ResourceLocalTransactionCoordinatorImpl#afterBeginCallback" );
	}
