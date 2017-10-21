	@SuppressWarnings( {""})
	protected void addConfigOptions(Map options) {
		TransactionCoordinatorBuilder transactionCoordinatorBuilder = Mockito.mock( TransactionCoordinatorBuilder.class);
		when(transactionCoordinatorBuilder.getDefaultConnectionHandlingMode())
		.thenReturn( PhysicalConnectionHandlingMode.DELAYED_ACQUISITION_AND_HOLD );

		when(transactionCoordinatorBuilder.isJta())
		.thenReturn( false );

		transactionCoordinator = Mockito.mock( TransactionCoordinator.class);

		when(transactionCoordinatorBuilder.buildTransactionCoordinator(any(), any( TransactionCoordinatorBuilder.Options.class)))
		.thenReturn( transactionCoordinator );

		when( transactionCoordinator.getTransactionCoordinatorBuilder() ).thenReturn( transactionCoordinatorBuilder );

		TransactionCoordinator.TransactionDriver transactionDriver = Mockito.mock( TransactionCoordinator.TransactionDriver.class);
		when( transactionCoordinator.getTransactionDriverControl() ).thenReturn( transactionDriver );
		when( transactionCoordinator.isActive() ).thenReturn( true );
		when( transactionDriver.isActive( anyBoolean() ) ).thenReturn( false );

		doNothing().when( transactionCoordinator ).pulse();

		options.put( Environment.TRANSACTION_COORDINATOR_STRATEGY, transactionCoordinatorBuilder );
	}
