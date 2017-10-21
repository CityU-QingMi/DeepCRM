	@Override
	public Transaction accessTransaction() {
		if ( this.currentHibernateTransaction == null || this.currentHibernateTransaction.getStatus() != TransactionStatus.ACTIVE ) {
			this.currentHibernateTransaction = new TransactionImpl(
					getTransactionCoordinator(),
					getExceptionConverter()
			);

		}
		if ( !isClosed() || (waitingForAutoClose && factory.isOpen()) ) {
			getTransactionCoordinator().pulse();
		}
		return this.currentHibernateTransaction;
	}
