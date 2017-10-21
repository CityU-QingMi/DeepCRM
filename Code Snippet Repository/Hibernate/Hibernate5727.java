	@Test
	public void testDeclaredIdClassAttributes( ) {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();

		// Packaging arguments for use in query.
		List<String> divisions = new ArrayList<String>( );
		divisions.add( "NA" );
		divisions.add( "EU" );
			
		// Building the query.
		CriteriaBuilder criteria = em.getCriteriaBuilder( );
		CriteriaQuery<Widget> query = criteria.createQuery( Widget.class );
		Root<Widget> root = query.from( Widget.class );
			
		Predicate predicate = root.get( "division" ).in( divisions );
		query.where( predicate );

		// Retrieving query.;
		List<Widget> widgets = em.createQuery( query ).getResultList( );
		Assert.assertEquals( 4, widgets.size() );

		em.getTransaction().commit();
		em.close();
	}
