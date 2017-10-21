	@Test(expected = RollbackException.class)
	@Priority(5)
	public void testTransactionRollback() throws Exception {
		TestingJtaPlatformImpl.INSTANCE.getTransactionManager().begin();

		try {
			EntityManager em = getEntityManager();

			// Trying to persist an entity - however the listener should throw an exception, so the entity
			// shouldn't be persisted
			StrTestEntity te = new StrTestEntity( "x" );
			em.persist( te );
		}
		finally {
			TestingJtaPlatformImpl.tryCommit();
		}
	}
