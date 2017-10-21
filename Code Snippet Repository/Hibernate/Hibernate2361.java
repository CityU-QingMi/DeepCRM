	@Override
	public void begin() {
		if ( !transactionCoordinator.isActive() ) {
			throw new TransactionException( "Cannot begin Transaction on closed Session/EntityManager" );
		}

		if ( transactionDriverControl == null ) {
			transactionDriverControl = transactionCoordinator.getTransactionDriverControl();
		}

		// per-JPA
		if ( isActive() ) {
			throw new IllegalStateException( "Transaction already active" );
		}

		LOG.debug( "begin" );
		this.transactionDriverControl.begin();
	}
