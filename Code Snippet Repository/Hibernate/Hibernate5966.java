	@Test
	@TestForIssue( jiraKey = "")
	@FailureExpected( jiraKey = "" )
	public void testReplacedWhileManaged() {
		createData();

		EntityManager entityManager = getOrCreateEntityManager();
		entityManager.getTransaction().begin();
		List results = entityManager.createQuery( "from Feature" ).getResultList();
		assertEquals( 1, results.size() );
		results = entityManager.createQuery( "from Product" ).getResultList();
		assertEquals( 1, results.size() );
		Product product = ( Product ) results.get( 0 );
		assertEquals( 1, product.getFeatures().size() );

		// Replace with a new Feature instance
		product.getFeatures().remove( 0 );
		Feature featureNew = new Feature();
		featureNew.setName( "Feature 2" );
		featureNew.setProduct( product );
		product.getFeatures().add( featureNew );
		entityManager.persist( featureNew );
		entityManager.getTransaction().commit();
		entityManager.close();

		entityManager = getOrCreateEntityManager();
		entityManager.getTransaction().begin();
		results = entityManager.createQuery( "from Feature" ).getResultList();
		assertEquals( 1, results.size() );
		Feature featureQueried = (Feature) results.get( 0 );
		assertEquals( featureNew.getId(), featureQueried.getId() );
		results = entityManager.createQuery( "from Product" ).getResultList();
		assertEquals( 1, results.size() );
		Product productQueried =  (Product) results.get( 0 );
		assertEquals( 1, productQueried.getFeatures().size() );
		assertEquals( featureQueried, productQueried.getFeatures().get( 0 ) );

		entityManager.getTransaction().commit();
		entityManager.close();

		cleanupData();
	}
