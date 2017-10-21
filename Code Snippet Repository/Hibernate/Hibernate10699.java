	@Test
	@Priority(10)
	public void initData() throws Exception {
		TestingJtaPlatformImpl.INSTANCE.getTransactionManager().begin();

		EntityManager em;
		IntTestEntity ite;
		try {
			em = getEntityManager();
			ite = new IntTestEntity( 10 );
			em.persist( ite );
			id1 = ite.getId();
		}
		finally {
			TestingJtaPlatformImpl.tryCommit();
		}
		em.close();

		TestingJtaPlatformImpl.INSTANCE.getTransactionManager().begin();

		try {
			em = getEntityManager();
			ite = em.find( IntTestEntity.class, id1 );
			ite.setNumber( 20 );
		}
		finally {
			TestingJtaPlatformImpl.tryCommit();
		}
		em.close();
	}
