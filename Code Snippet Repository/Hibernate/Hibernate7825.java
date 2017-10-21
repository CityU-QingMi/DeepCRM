	@Test
	public void testManyToManyOnCollectionLoadAfterHQL() {
		TestData testData = new TestData();
		testData.prepare();

		Session session = openSession();
		session.enableFilter( "effectiveDate" ).setParameter( "asOfDate", new Date() );

		// Force the categories to not get initialized here
		List result = session.createQuery( "from Product as p where p.id = :id" )
		        .setLong( "id", testData.prod1Id )
		        .list();
		assertTrue( "No products returned from HQL", !result.isEmpty() );

		Product prod = ( Product ) result.get( 0 );
		assertNotNull( prod );
		assertEquals( "Incorrect Product.categories count for filter on collection load", 1, prod.getCategories().size() );

		session.close();
		testData.release();
	}
