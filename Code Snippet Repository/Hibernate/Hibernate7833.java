	@Test
	public void testCriteriaQueryFilters() {
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		// Criteria-query test
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        log.info("Starting Criteria-query filter tests");
		TestData testData = new TestData();
		testData.prepare();

		Session session = openSession();
		session.enableFilter( "region" ).setParameter( "region", "APAC" );

		session.enableFilter( "fulfilledOrders" )
		        .setParameter( "asOfDate", testData.lastMonth.getTime() );

		session.enableFilter( "effectiveDate" )
		        .setParameter( "asOfDate", testData.lastMonth.getTime() );

        log.info("Criteria query against Salesperson...");
		List salespersons = session.createCriteria( Salesperson.class )
		        .setFetchMode( "orders", FetchMode.JOIN )
		        .list();
		assertEquals( "Incorrect salesperson count", 1, salespersons.size() );
		assertEquals( "Incorrect order count", 1, ( ( Salesperson ) salespersons.get( 0 ) ).getOrders().size() );

        log.info("Criteria query against Product...");
		List products = session.createCriteria( Product.class )
		        .add( Restrictions.eq( "stockNumber", 124 ) )
		        .list();
		assertEquals( "Incorrect product count", 1, products.size() );

		session.close();
		testData.release();
	}
