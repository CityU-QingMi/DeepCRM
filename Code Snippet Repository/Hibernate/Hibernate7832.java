	@Test
	public void testFiltersWithCustomerReadAndWrite() {
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		// Custom SQL read/write with filter
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        log.info("Starting HQL filter with custom SQL get/set tests");
		TestData testData = new TestData();
		testData.prepare();

		Session session = openSession();
		session.enableFilter( "heavyProducts" ).setParameter("weightKilograms", 4d);
        log.info( "HQL against Product..." );
		List results = session.createQuery( "from Product").list();
		assertEquals( 1, results.size() );

		session.close();
		testData.release();
	}
