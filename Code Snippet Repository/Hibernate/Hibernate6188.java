	@Test
	@TestForIssue(jiraKey = "")
	public void testIsJoinedAfterMarkedForRollbackExplicit() throws Exception {

		assertFalse( JtaStatusHelper.isActive( TestingJtaPlatformImpl.INSTANCE.getTransactionManager() ) );

		EntityManager entityManager = entityManagerFactory().createEntityManager( SynchronizationType.UNSYNCHRONIZED );
		SharedSessionContractImplementor session = entityManager.unwrap( SharedSessionContractImplementor.class );
		assertTrue( entityManager.isOpen() );
		assertTrue( session.isOpen() );

		ExtraAssertions.assertTyping( JtaTransactionCoordinatorImpl.class, session.getTransactionCoordinator() );
		JtaTransactionCoordinatorImpl transactionCoordinator = (JtaTransactionCoordinatorImpl) session.getTransactionCoordinator();

		TestingJtaPlatformImpl.INSTANCE.getTransactionManager().begin();
		entityManager.joinTransaction();

		assertTrue( transactionCoordinator.isSynchronizationRegistered() );
		assertTrue( transactionCoordinator.isActive() );
		assertTrue( transactionCoordinator.isJoined() );

		transactionCoordinator.getTransactionDriverControl().markRollbackOnly();

		assertTrue( transactionCoordinator.isActive() );
		assertTrue( transactionCoordinator.isJoined() );
		assertTrue( entityManager.isJoinedToTransaction() );

		TestingJtaPlatformImpl.INSTANCE.getTransactionManager().rollback();

		entityManager.close();
		assertFalse( entityManager.isOpen() );
		assertFalse( session.isOpen() );
	}
