	@Test
	public void testManyToManyBaseThruCriteria() {
		TestData testData = new TestData();
		testData.prepare();

		Session session = openSession();

		List result = session.createCriteria( Product.class )
		        .add( Restrictions.eq( "id", testData.prod1Id ) )
		        .list();

		Product prod = ( Product ) result.get( 0 );

		long initLoadCount = sessionFactory().getStatistics().getCollectionLoadCount();
		long initFetchCount = sessionFactory().getStatistics().getCollectionFetchCount();

		// should already have been initialized...
		int size = prod.getCategories().size();
		assertEquals( "Incorrect non-filtered collection count", 2, size );

		long currLoadCount = sessionFactory().getStatistics().getCollectionLoadCount();
		long currFetchCount = sessionFactory().getStatistics().getCollectionFetchCount();

		assertTrue(
		        "load with join fetch of many-to-many did not trigger join fetch",
		        ( initLoadCount == currLoadCount ) && ( initFetchCount == currFetchCount )
		);

		// make sure we did not get back a collection of proxies
		long initEntityLoadCount = sessionFactory().getStatistics().getEntityLoadCount();
		Iterator itr = prod.getCategories().iterator();
		while ( itr.hasNext() ) {
			Category cat = ( Category ) itr.next();
			System.out.println( " ===> " + cat.getName() );
		}
		long currEntityLoadCount = sessionFactory().getStatistics().getEntityLoadCount();

		assertTrue(
		        "load with join fetch of many-to-many did not trigger *complete* join fetch",
		        ( initEntityLoadCount == currEntityLoadCount )
		);

		session.close();
		testData.release();
	}
