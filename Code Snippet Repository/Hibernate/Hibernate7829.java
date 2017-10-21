	@Test
	public void testCombinedClassAndCollectionFiltersEnabled() {
		TestData testData = new TestData();
		testData.prepare();

		Session session = openSession();
		session.enableFilter( "regionlist" ).setParameterList( "regions", new String[]{"LA", "APAC"} );
		session.enableFilter( "fulfilledOrders" ).setParameter( "asOfDate", testData.lastMonth.getTime() );

		// test retreival through hql with the collection as non-eager
		List salespersons = session.createQuery( "select s from Salesperson as s" ).list();
		assertEquals( "Incorrect salesperson count", 1, salespersons.size() );
		Salesperson sp = ( Salesperson ) salespersons.get( 0 );
		assertEquals( "Incorrect order count", 1, sp.getOrders().size() );

		session.clear();

		session.disableFilter( "regionlist" );
		session.enableFilter( "regionlist" ).setParameterList( "regions", new String[]{"LA", "APAC", "APAC"} );
		// Second test retreival through hql with the collection as non-eager with different region list
		salespersons = session.createQuery( "select s from Salesperson as s" ).list();
		assertEquals( "Incorrect salesperson count", 1, salespersons.size() );
		sp = ( Salesperson ) salespersons.get( 0 );
		assertEquals( "Incorrect order count", 1, sp.getOrders().size() );

		session.clear();


		// test retreival through hql with the collection join fetched
		salespersons = session.createQuery( "select s from Salesperson as s left join fetch s.orders" ).list();
		assertEquals( "Incorrect salesperson count", 1, salespersons.size() );
		sp = ( Salesperson ) salespersons.get( 0 );
		assertEquals( "Incorrect order count", 1, sp.getOrders().size() );

		session.close();
		testData.release();
	}
