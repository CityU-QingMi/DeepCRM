	static void validateExplicitJoiningHandling(EntityManager entityManager) throws Exception {
		SharedSessionContractImplementor session = entityManager.unwrap( SharedSessionContractImplementor.class );

		ExtraAssertions.assertTyping( JtaTransactionCoordinatorImpl.class, session.getTransactionCoordinator() );
		JtaTransactionCoordinatorImpl transactionCoordinator = (JtaTransactionCoordinatorImpl) session.getTransactionCoordinator();

		assertFalse( transactionCoordinator.isSynchronizationRegistered() );
		assertFalse( transactionCoordinator.isJtaTransactionCurrentlyActive() );
		assertFalse( transactionCoordinator.isJoined() );

		session.getFlushMode();
		assertFalse( transactionCoordinator.isSynchronizationRegistered() );
		assertFalse( transactionCoordinator.isJtaTransactionCurrentlyActive() );
		assertFalse( transactionCoordinator.isJoined() );

		TestingJtaPlatformImpl.INSTANCE.getTransactionManager().begin();
		assertTrue( JtaStatusHelper.isActive( TestingJtaPlatformImpl.INSTANCE.getTransactionManager() ) );
		assertTrue( transactionCoordinator.isJtaTransactionCurrentlyActive() );
		assertFalse( transactionCoordinator.isJoined() );
		assertFalse( transactionCoordinator.isSynchronizationRegistered() );

		session.getFlushMode();
		assertTrue( JtaStatusHelper.isActive( TestingJtaPlatformImpl.INSTANCE.getTransactionManager() ) );
		assertTrue( transactionCoordinator.isJtaTransactionCurrentlyActive() );
		assertFalse( transactionCoordinator.isJoined() );
		assertFalse( transactionCoordinator.isSynchronizationRegistered() );

		entityManager.joinTransaction();
		assertTrue( JtaStatusHelper.isActive( TestingJtaPlatformImpl.INSTANCE.getTransactionManager() ) );
		assertTrue( transactionCoordinator.isJtaTransactionCurrentlyActive() );
		assertTrue( transactionCoordinator.isSynchronizationRegistered() );
		assertTrue( transactionCoordinator.isJoined() );

		assertTrue( entityManager.isOpen() );
		assertTrue( session.isOpen() );
		entityManager.close();
		assertFalse( entityManager.isOpen() );
		assertFalse( session.isOpen() );

		TestingJtaPlatformImpl.INSTANCE.getTransactionManager().commit();
		assertFalse( entityManager.isOpen() );
		assertFalse( session.isOpen() );
	}
