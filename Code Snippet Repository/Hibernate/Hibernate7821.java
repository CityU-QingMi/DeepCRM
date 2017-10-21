	@Test
	public void testOneToManyFilters() {
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		// one-to-many loading tests
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        log.info("Starting one-to-many collection loader filter tests.");
		TestData testData = new TestData();
		testData.prepare();

		Session session = openSession();
		session.enableFilter( "seniorSalespersons" )
		        .setParameter( "asOfDate", testData.lastMonth.getTime() );

        log.info("Performing load of Department...");
		Department department = ( Department ) session.load( Department.class, testData.deptId );
		Set salespersons = department.getSalespersons();
		assertEquals( "Incorrect salesperson count", 1, salespersons.size() );

		session.close();
		testData.release();
	}
