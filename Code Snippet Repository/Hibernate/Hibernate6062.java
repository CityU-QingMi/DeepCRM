	@Test
	public void testFlushModeHandling() {
		final String name = "flush-mode-handling";

		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();

		Query q = em.createQuery( "from Item" );
		assertEquals( FlushModeType.AUTO, q.getFlushMode() );
		q.setFlushMode( FlushModeType.COMMIT );
		assertEquals( FlushModeType.COMMIT, q.getFlushMode() );
		em.getEntityManagerFactory().addNamedQuery( name, q );

		// first, lets check the underlying stored query def
		SessionFactoryImplementor sfi = entityManagerFactory().unwrap( SessionFactoryImplementor.class );
		NamedQueryDefinition def = sfi.getNamedQueryRepository().getNamedQueryDefinition( name );
		assertEquals( FlushMode.COMMIT, def.getFlushMode() );

		// then lets create a query by name and check its setting
		q = em.createNamedQuery( name );
		assertEquals( FlushMode.COMMIT, q.unwrap( org.hibernate.Query.class ).getHibernateFlushMode() );
		assertEquals( FlushModeType.COMMIT, q.getFlushMode() );

		em.getTransaction().commit();
		em.close();
	}
