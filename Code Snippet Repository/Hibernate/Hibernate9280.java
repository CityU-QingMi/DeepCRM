	@Test
	@SuppressWarnings("")
	public void testMarkRollbackOnly() {
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

		assertEquals( TransactionStatus.NOT_ACTIVE, transactionCoordinator.getTransactionDriverControl().getStatus() );

		transactionCoordinator.getTransactionDriverControl().begin();
		assertEquals( TransactionStatus.ACTIVE, transactionCoordinator.getTransactionDriverControl().getStatus() );

		transactionCoordinator.getTransactionDriverControl().markRollbackOnly();
		assertEquals( TransactionStatus.MARKED_ROLLBACK, transactionCoordinator.getTransactionDriverControl().getStatus() );

		try {
			transactionCoordinator.getTransactionDriverControl().commit();
		}
		catch (TransactionException expected) {
		}
		finally {
			assertEquals( TransactionStatus.NOT_ACTIVE, transactionCoordinator.getTransactionDriverControl().getStatus() );
		}
	}
