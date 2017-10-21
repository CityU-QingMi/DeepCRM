	@Test
	@SuppressWarnings("")
	public void testSynchronizationFailure() throws Exception {
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

		assertEquals( TransactionStatus.NOT_ACTIVE, transactionCoordinator.getTransactionDriverControl().getStatus() );
		transactionCoordinator.getLocalSynchronizations().registerSynchronization( SynchronizationErrorImpl.forBefore() );

		transactionCoordinator.getTransactionDriverControl().begin();
		assertEquals( TransactionStatus.ACTIVE, transactionCoordinator.getTransactionDriverControl().getStatus() );

		try {
			transactionCoordinator.getTransactionDriverControl().commit();
		}
		catch (Exception expected) {
		}
		finally {
			assertEquals( TransactionStatus.NOT_ACTIVE, transactionCoordinator.getTransactionDriverControl().getStatus() );
		}
	}
