	@Test
	@TestForIssue(jiraKey = "")
	public void testIsJoinedAfterMarkedForRollbackImplict() throws Exception {
		assertFalse( JtaStatusHelper.isActive( TestingJtaPlatformImpl.INSTANCE.getTransactionManager() ) );

		TestingJtaPlatformImpl.INSTANCE.getTransactionManager().begin();
		EntityManager entityManager = entityManagerFactory().createEntityManager();
		SharedSessionContractImplementor session = entityManager.unwrap( SharedSessionContractImplementor.class );

		ExtraAssertions.assertTyping( JtaTransactionCoordinatorImpl.class, session.getTransactionCoordinator() );
		JtaTransactionCoordinatorImpl transactionCoordinator = (JtaTransactionCoordinatorImpl) session.getTransactionCoordinator();

		assertTrue( transactionCoordinator.isSynchronizationRegistered() );
		assertTrue( transactionCoordinator.isActive() );
		assertTrue( transactionCoordinator.isJoined() );

		assertTrue( entityManager.isOpen() );
		assertTrue( session.isOpen() );
		transactionCoordinator.getTransactionDriverControl().markRollbackOnly();

		assertTrue( transactionCoordinator.isActive() );
		assertTrue( transactionCoordinator.isJoined() );
		assertTrue( entityManager.isJoinedToTransaction() );

		TestingJtaPlatformImpl.INSTANCE.getTransactionManager().rollback();

		entityManager.close();
		assertFalse( entityManager.isOpen() );
		assertFalse( session.isOpen() );
	}
