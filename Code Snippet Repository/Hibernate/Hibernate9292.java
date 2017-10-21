	@Test
	public void rollbackCmtUsageTest() throws Exception {
		// pre conditions
		final TransactionManager tm = JtaPlatformStandardTestingImpl.INSTANCE.transactionManager();
		assertEquals( Status.STATUS_NO_TRANSACTION, tm.getStatus() );

		// begin the transaction
		tm.begin();
		assertEquals( Status.STATUS_ACTIVE, tm.getStatus() );

		final JtaTransactionCoordinatorImpl transactionCoordinator = buildTransactionCoordinator( true );
		// NOTE : because of auto-join
		assertTrue( transactionCoordinator.isSynchronizationRegistered() );

		// create and add a local Synchronization
		SynchronizationCollectorImpl localSync = new SynchronizationCollectorImpl();
		transactionCoordinator.getLocalSynchronizations().registerSynchronization( localSync );

		// rollback the transaction
		tm.rollback();

		// post conditions
		assertEquals( Status.STATUS_NO_TRANSACTION, tm.getStatus() );
		assertFalse( transactionCoordinator.isSynchronizationRegistered() );
		assertEquals( 0, localSync.getBeforeCompletionCount() );
		assertEquals( 0, localSync.getSuccessfulCompletionCount() );
		assertEquals( 1, localSync.getFailedCompletionCount() );
	}
