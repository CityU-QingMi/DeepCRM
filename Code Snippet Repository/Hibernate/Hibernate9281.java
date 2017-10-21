	@Test
	@SuppressWarnings("")
	public void testSynchronizationFailure() {
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
		transactionCoordinator.getLocalSynchronizations().registerSynchronization( SynchronizationErrorImpl.forBefore() );

		transactionCoordinator.getTransactionDriverControl().begin();
		assertEquals( TransactionStatus.ACTIVE, transactionCoordinator.getTransactionDriverControl().getStatus() );

		try {
			transactionCoordinator.getTransactionDriverControl().commit();
		}
		catch (Exception expected) {
		}
		finally {
			assertEquals(
					TransactionStatus.NOT_ACTIVE,
					transactionCoordinator.getTransactionDriverControl().getStatus()
			);
		}
	}
