	@Test
	public void testLockTimeoutASNamedQueryHint(){
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		Query query = em.createNamedQuery( "getAll" );
		query.setLockMode( LockModeType.PESSIMISTIC_READ );

		int timeout = query.unwrap( org.hibernate.query.Query.class ).getLockOptions().getTimeOut();
		assertEquals( 3000, timeout );
	}
