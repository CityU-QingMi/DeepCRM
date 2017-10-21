	@Test
	public void testGetFilters() {
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		// Get() test
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        log.info("Starting get() filter tests (eager assoc. fetching).");
		TestData testData = new TestData();
		testData.prepare();

		Session session = openSession();
		session.enableFilter( "region" ).setParameter( "region", "APAC" );

        log.info("Performing get()...");
		Salesperson salesperson = ( Salesperson ) session.get( Salesperson.class, testData.steveId );
		assertNotNull( salesperson );
		assertEquals( "Incorrect order count", 1, salesperson.getOrders().size() );

		session.close();
		testData.release();
	}
