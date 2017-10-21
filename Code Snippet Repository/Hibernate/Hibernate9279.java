	@Test
	public void basicUsageTest() throws Exception {
		final TransactionCoordinatorOwnerTestingImpl owner = new TransactionCoordinatorOwnerTestingImpl();
		final JdbcResourceLocalTransactionCoordinatorBuilderImpl transactionCoordinatorBuilder =
				new JdbcResourceLocalTransactionCoordinatorBuilderImpl();

		final TransactionCoordinator transactionCoordinator = transactionCoordinatorBuilder.buildTransactionCoordinator(
				owner,
				new TransactionCoordinatorBuilder.Options() {
					@Override
					public boolean shouldAutoJoinTransaction() {
						return false;
					}
				}
		);

		SynchronizationCollectorImpl sync = new SynchronizationCollectorImpl();
		transactionCoordinator.getLocalSynchronizations().registerSynchronization( sync );

		transactionCoordinator.getTransactionDriverControl().begin();
		assertEquals( 0, sync.getBeforeCompletionCount() );
		assertEquals( 0, sync.getSuccessfulCompletionCount() );
		assertEquals( 0, sync.getFailedCompletionCount() );

		transactionCoordinator.getTransactionDriverControl().commit();
		assertEquals( 1, sync.getBeforeCompletionCount() );
		assertEquals( 1, sync.getSuccessfulCompletionCount() );
		assertEquals( 0, sync.getFailedCompletionCount() );

	}
