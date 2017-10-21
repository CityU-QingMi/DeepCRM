	@Test
	@SuppressWarnings("")
	public void testExplicitJoiningTransactionRequiredException() throws Exception {
		// explicitly calling EntityManager#joinTransaction outside of an active transaction should cause
		// a TransactionRequiredException to be thrown

		EntityManager entityManager = entityManagerFactory().createEntityManager();
		assertFalse("setup problem", JtaStatusHelper.isActive(TestingJtaPlatformImpl.INSTANCE.getTransactionManager()));

		try {
			entityManager.joinTransaction();
			fail( "Expected joinTransaction() to fail since there is no active JTA transaction" );
		}
		catch (TransactionRequiredException expected) {
		}
	}
