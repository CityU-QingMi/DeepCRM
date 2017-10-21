	@Test
	public void basicThreadCheckingUsage() throws Exception {
		JtaTransactionCoordinatorImpl transactionCoordinator = new JtaTransactionCoordinatorImpl(
				transactionCoordinatorBuilder,
				owner,
				true,
				JtaPlatformStandardTestingImpl.INSTANCE,
				preferUserTransactions(),
				true
		);

		// pre conditions
		final TransactionManager tm = JtaPlatformStandardTestingImpl.INSTANCE.transactionManager();
		assertEquals( Status.STATUS_NO_TRANSACTION, tm.getStatus() );

		// begin the transaction
		tm.begin();
		transactionCoordinator.explicitJoin();
		assertEquals(
				SynchronizationCallbackCoordinatorTrackingImpl.class,
				transactionCoordinator.getSynchronizationCallbackCoordinator().getClass()
		);

		tm.commit();


		assertEquals( Status.STATUS_NO_TRANSACTION, tm.getStatus() );
		assertFalse( transactionCoordinator.isJoined() );

		tm.begin();
		transactionCoordinator.explicitJoin();
		assertEquals(
				SynchronizationCallbackCoordinatorTrackingImpl.class,
				transactionCoordinator.getSynchronizationCallbackCoordinator().getClass()
		);

		tm.rollback();
	}
