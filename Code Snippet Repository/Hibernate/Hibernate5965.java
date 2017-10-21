	@Test
	@TestForIssue( jiraKey = "")
	@FailureExpected( jiraKey = "" )
	public void testOrphanedWhileManagedMergeOwner() {
		createData();

		EntityManager entityManager = getOrCreateEntityManager();
		entityManager.getTransaction().begin();
		List results = entityManager.createQuery( "from Feature" ).getResultList();
		assertEquals( 1, results.size() );
		results = entityManager.createQuery( "from Product" ).getResultList();
		assertEquals( 1, results.size() );
		Product product = ( Product ) results.get( 0 );
		assertEquals( 1, product.getFeatures().size() );
		product.getFeatures().clear();
		entityManager.merge( product );
		entityManager.getTransaction().commit();
		entityManager.close();

		entityManager = getOrCreateEntityManager();
		entityManager.getTransaction().begin();

		product = entityManager.find( Product.class, product.getId() );
		assertEquals( 0, product.getFeatures().size() );
		results = entityManager.createQuery( "from Feature" ).getResultList();
		assertEquals( 0, results.size() );
		results = entityManager.createQuery( "from Product" ).getResultList();
		assertEquals( 1, results.size() );

		entityManager.getTransaction().commit();
		entityManager.close();

		cleanupData();
	}
