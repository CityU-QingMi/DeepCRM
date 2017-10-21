	@Test
	public void testHqlFilters() {
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		// HQL test
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        log.info( "Starting HQL filter tests" );
		TestData testData = new TestData();
		testData.prepare();

		Session session = openSession();
		session.enableFilter( "region" ).setParameter( "region", "APAC" );

		session.enableFilter( "effectiveDate" )
		        .setParameter( "asOfDate", testData.lastMonth.getTime() );

        log.info( "HQL against Salesperson..." );
		List results = session.createQuery( "select s from Salesperson as s left join fetch s.orders" ).list();
		assertTrue( "Incorrect filtered HQL result count [" + results.size() + "]", results.size() == 1 );
		Salesperson result = ( Salesperson ) results.get( 0 );
		assertTrue( "Incorrect collectionfilter count", result.getOrders().size() == 1 );

        log.info( "HQL against Product..." );
		results = session.createQuery( "from Product as p where p.stockNumber = ?" ).setInteger( 0, 124 ).list();
		assertTrue( results.size() == 1 );

		session.close();
		testData.release();
	}
