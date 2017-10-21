	@Test
	public void testCountIdClassAttributes(){
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<Widget> path = cq.from(Widget.class);
		Expression<Long> countSelection = cb.count(path);
		cq.select(countSelection);
		Long count = em.createQuery(cq).getSingleResult();
//		// Packaging arguments for use in query.
//		List<String> divisions = new ArrayList<String>( );
//		divisions.add( "NA" );
//		divisions.add( "EU" );
//
//		// Building the query.
//		CriteriaBuilder criteria = em.getCriteriaBuilder( );
//		CriteriaQuery<Widget> query = criteria.createQuery( Widget.class );
//		Root<Widget> root = query.from( Widget.class );
//
//		Predicate predicate = root.get( "division" ).in( divisions );
//		query.where( predicate );
//
//		// Retrieving query.;
//		List<Widget> widgets = em.createQuery( query ).getResultList( );
//		Assert.assertEquals( 4, widgets.size() );

		em.getTransaction().commit();
		em.close();
	}
