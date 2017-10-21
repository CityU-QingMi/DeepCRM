	@Test
	@TestForIssue( jiraKey = "")
	public void testOrphanedWhileManagedMergeOwner() {
		createData();

		Session session = openSession();
		session.beginTransaction();
		List results = session.createQuery( "from Feature" ).list();
		assertEquals( 1, results.size() );
		results = session.createQuery( "from Product" ).list();
		assertEquals( 1, results.size() );
		Product product = ( Product ) results.get( 0 );
		assertEquals( 1, product.getFeatures().size() );
		product.getFeatures().clear();
		session.merge( product );
		session.getTransaction().commit();
		session.close();

		session = openSession();
		session.beginTransaction();

		product = ( Product ) session.get( Product.class, product.getId() );
		assertEquals( 0, product.getFeatures().size() );
		results = session.createQuery( "from Feature" ).list();
		assertEquals( 0, results.size() );
		results = session.createQuery( "from Product" ).list();
		assertEquals( 1, results.size() );

		session.getTransaction().commit();
		session.close();

		cleanupData();
	}
