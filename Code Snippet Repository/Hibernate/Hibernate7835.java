	@Test
	public void testFilterApplicationOnHqlQueryWithImplicitSubqueryContainingPositionalParameter() {
		TestData testData = new TestData();
		testData.prepare();

		Session session = openSession();
		session.beginTransaction();

		final String queryString = "from Order o where ? in ( select sp.name from Salesperson sp )";

		// first a control-group query
		List result = session.createQuery( queryString ).setParameter( 0, "steve" ).list();
		assertEquals( 2, result.size() );

		// now lets enable filters on Order...
		session.enableFilter( "fulfilledOrders" ).setParameter( "asOfDate", testData.lastMonth.getTime() );
		result = session.createQuery( queryString ).setParameter( 0, "steve" ).list();
		assertEquals( 1, result.size() );

		// now, lets additionally enable filter on Salesperson.  First a valid one...
		session.enableFilter( "regionlist" ).setParameterList( "regions", new String[] { "APAC" } );
		result = session.createQuery( queryString ).setParameter( 0, "steve" ).list();
		assertEquals( 1, result.size() );

		// ... then a silly one...
		session.enableFilter( "regionlist" ).setParameterList( "regions", new String[] { "gamma quadrant" } );
		result = session.createQuery( queryString ).setParameter( 0, "steve" ).list();
		assertEquals( 0, result.size() );

		session.getTransaction().commit();
		session.close();

		testData.release();
	}
