	@Test
	public void testEntityGraphWithExplicitFetch() {
		EntityManager entityManager = getOrCreateEntityManager();
		entityManager.getTransaction().begin();
		
		EntityGraph<Company> entityGraph = entityManager.createEntityGraph( Company.class );
		entityGraph.addAttributeNodes( "location" );
		entityGraph.addAttributeNodes( "markets" );
		entityGraph.addAttributeNodes( "employees" );
		// Ensure the EntityGraph and explicit fetches do not conflict.
		Query query = entityManager.createQuery( "from " + Company.class.getName()
				+ " as c left join fetch c.location left join fetch c.employees" );
		query.setHint( QueryHints.HINT_LOADGRAPH, entityGraph );
		Company company = (Company) query.getSingleResult();
		
		entityManager.getTransaction().commit();
		entityManager.close();
		
		assertTrue( Hibernate.isInitialized( company.employees ) );
		assertTrue( Hibernate.isInitialized( company.location ) );
		assertTrue( Hibernate.isInitialized( company.markets ) );
		// With "loadgraph", non-specified attributes use the fetch modes defined in the mappings.  So, here,
		// @ElementCollection(fetch = FetchType.EAGER) should cause the follow-on selects to happen.
		assertTrue( Hibernate.isInitialized( company.phoneNumbers ) );
	}
