	@Test
	public void assureMultipleJoinCallsNoOp() throws Exception {
		// pre conditions
		final TransactionManager tm = JtaPlatformStandardTestingImpl.INSTANCE.transactionManager();
		assertEquals( Status.STATUS_NO_TRANSACTION, tm.getStatus() );

		// begin the transaction
		tm.begin();

		assertEquals( Status.STATUS_ACTIVE, tm.getStatus() );

		final JtaTransactionCoordinatorImpl transactionCoordinator = buildTransactionCoordinator( false );
		// no auto-join now
		assertFalse( transactionCoordinator.isJoined() );
		transactionCoordinator.explicitJoin();
		assertTrue( transactionCoordinator.isJoined() );
		transactionCoordinator.explicitJoin();
		transactionCoordinator.explicitJoin();
		transactionCoordinator.explicitJoin();
		transactionCoordinator.explicitJoin();
		assertTrue( transactionCoordinator.isJoined() );

		// create and add a local Synchronization
		SynchronizationCollectorImpl localSync = new SynchronizationCollectorImpl();
		transactionCoordinator.getLocalSynchronizations().registerSynchronization( localSync );

		// commit the transaction
		tm.commit();

		// post conditions
		assertEquals( Status.STATUS_NO_TRANSACTION, tm.getStatus() );
		assertFalse( transactionCoordinator.isSynchronizationRegistered() );
		assertEquals( 1, localSync.getBeforeCompletionCount() );
		assertEquals( 1, localSync.getSuccessfulCompletionCount() );
		assertEquals( 0, localSync.getFailedCompletionCount() );
	}
