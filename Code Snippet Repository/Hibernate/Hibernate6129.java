	@Test
	public void testEntityGraphAndInClause() {
		// this test fails
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		Set<Long> ids = new HashSet<Long>();
		ids.add( 1L );
		ids.add( 2L );
		TypedQuery<Person> query = em.createQuery( "select p from Person p where p.id  in :ids", Person.class );
		query.setHint( "javax.persistence.loadgraph", em.createEntityGraph( "withBoss" ) );
		query.setParameter( "ids", ids );
		query.getResultList();
		em.getTransaction().commit();
		em.close();
	}
