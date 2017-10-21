	@Test
	@Priority(10)
	public void initData() throws Exception {
		TestingJtaPlatformImpl.INSTANCE.getTransactionManager().begin();
		EntityManager entityManager = getEntityManager();
		try {
			IntTestEntity ite = new IntTestEntity( 10 );
			entityManager.persist( ite );
			entityId = ite.getId();
			// simulates spring JtaTransactionManager.triggerBeforeCompletion()
			// this closes the entity manager prior to the JTA transaction.
			entityManager.close();
		}
		finally {
			TestingJtaPlatformImpl.tryCommit();
		}
	}
