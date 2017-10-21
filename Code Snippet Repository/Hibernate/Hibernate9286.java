	@Test
	@SuppressWarnings("")
	public void testMarkRollbackOnly() throws Exception {
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

		transactionCoordinator.getTransactionDriverControl().begin();
		assertEquals( TransactionStatus.ACTIVE, transactionCoordinator.getTransactionDriverControl().getStatus() );

		transactionCoordinator.getTransactionDriverControl().markRollbackOnly();
		assertEquals(
				TransactionStatus.MARKED_ROLLBACK,
				transactionCoordinator.getTransactionDriverControl().getStatus()
		);

		try {
			transactionCoordinator.getTransactionDriverControl().commit();
		}
		catch (TransactionException expected) {
		}
		finally {
			assertEquals( TransactionStatus.NOT_ACTIVE, transactionCoordinator.getTransactionDriverControl().getStatus() );
		}
	}
