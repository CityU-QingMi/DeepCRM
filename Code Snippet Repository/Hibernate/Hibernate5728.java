	@Test
	public void testSupertypeIdClassAttributes( ) {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();

		// Packaging arguments for use in query.
		List<String> types = new ArrayList<String>( );
		types.add( "NA" );
		types.add( "EU" );

		// Building the query.
		CriteriaBuilder criteria = em.getCriteriaBuilder( );
		CriteriaQuery<Tool> query = criteria.createQuery( Tool.class );
		Root<Tool> root = query.from( Tool.class );

		Predicate predicate = root.get( "type" ).in( types );
		query.where( predicate );

		// Retrieving query.
		List<Tool> tools = em.createQuery( query ).getResultList( );
		Assert.assertEquals( 4, tools.size() );

		em.getTransaction().commit();
		em.close();
	}
