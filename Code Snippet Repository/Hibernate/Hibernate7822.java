	@Test
	public void testInStyleFilterParameter() {
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		// one-to-many loading tests
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        log.info("Starting one-to-many collection loader filter tests.");
		TestData testData = new TestData();
		testData.prepare();

		Session session = openSession();
		session.enableFilter( "regionlist" )
		        .setParameterList( "regions", new String[]{"LA", "APAC"} );

        log.debug("Performing query of Salespersons");
		List salespersons = session.createQuery( "from Salesperson" ).list();
		assertEquals( "Incorrect salesperson count", 1, salespersons.size() );

		session.close();
		testData.release();
	}
