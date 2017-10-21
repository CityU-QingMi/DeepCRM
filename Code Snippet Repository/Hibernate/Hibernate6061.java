	@Test
	public void testLockModeHandling() {
		final String name = "lock-mode-handling";

		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();

		Query q = em.createQuery( "from Item" );
		assertEquals( LockModeType.NONE, q.getLockMode() );
		q.setLockMode( LockModeType.OPTIMISTIC );
		assertEquals( LockModeType.OPTIMISTIC, q.getLockMode() );
		em.getEntityManagerFactory().addNamedQuery( name, q );

		// first, lets check the underlying stored query def
		SessionFactoryImplementor sfi = entityManagerFactory().unwrap( SessionFactoryImplementor.class );
		NamedQueryDefinition def = sfi.getNamedQueryRepository().getNamedQueryDefinition( name );
		assertEquals( LockMode.OPTIMISTIC, def.getLockOptions().getLockMode() );

		// then lets create a query by name and check its setting
		q = em.createNamedQuery( name );
		assertEquals( LockMode.OPTIMISTIC, q.unwrap( org.hibernate.Query.class ).getLockOptions().getLockMode() );
		assertEquals( LockModeType.OPTIMISTIC, q.getLockMode() );

		em.getTransaction().commit();
		em.close();
	}
