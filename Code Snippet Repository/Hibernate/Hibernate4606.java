	public JtaTransactionCoordinatorImpl(
			TransactionCoordinatorBuilder transactionCoordinatorBuilder,
			TransactionCoordinatorOwner owner,
			boolean autoJoinTransactions,
			JtaPlatform jtaPlatform,
			boolean preferUserTransactions,
			boolean performJtaThreadTracking,
			TransactionObserver... observers) {
		this.observers = new ArrayList<TransactionObserver>();
		this.transactionCoordinatorBuilder = transactionCoordinatorBuilder;
		this.transactionCoordinatorOwner = owner;
		this.autoJoinTransactions = autoJoinTransactions;
		this.jtaPlatform = jtaPlatform;
		this.preferUserTransactions = preferUserTransactions;
		this.performJtaThreadTracking = performJtaThreadTracking;

		if ( observers != null ) {
			Collections.addAll( this.observers, observers );
		}

		synchronizationRegistered = false;

		pulse();

	}
