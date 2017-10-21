	public JtaTransactionCoordinatorImpl buildTransactionCoordinator(boolean autoJoin) {
		return new JtaTransactionCoordinatorImpl(
				transactionCoordinatorBuilder,
				owner,
				autoJoin,
				JtaPlatformStandardTestingImpl.INSTANCE,
				preferUserTransactions(),
				false
		);
	}
