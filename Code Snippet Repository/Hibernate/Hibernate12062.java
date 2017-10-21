	private void completeStrayTransaction() {
		if ( session == null ) {
			// nothing to do
			return;
		}

		if ( ( (SessionImplementor) session ).isClosed() ) {
			// nothing to do
			return;
		}

		if ( !session.isConnected() ) {
			// nothing to do
			return;
		}

		final TransactionCoordinator.TransactionDriver tdc =
				( (SessionImplementor) session ).getTransactionCoordinator().getTransactionDriverControl();

		if ( tdc.getStatus().canRollback() ) {
			session.getTransaction().rollback();
		}
	}
