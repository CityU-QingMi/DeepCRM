	@Test
	public void testCriteriaControl() {
		TestData testData = new TestData();
		testData.prepare();

		// the subquery...
		DetachedCriteria subquery = DetachedCriteria.forClass( Salesperson.class )
				.setProjection( Property.forName( "name" ) );

		Session session = openSession();
		session.beginTransaction();
		session.enableFilter( "fulfilledOrders" ).setParameter( "asOfDate", testData.lastMonth.getTime() );
		session.enableFilter( "regionlist" ).setParameterList( "regions", new String[] {"APAC"} );

		List result = session.createCriteria( Order.class )
				.add( Subqueries.in( "steve", subquery ) )
				.list();
		assertEquals( 1, result.size() );

		session.getTransaction().commit();
		session.close();

		testData.release();
	}
