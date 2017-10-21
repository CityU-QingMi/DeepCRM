	@Test
	@FailureExpected( jiraKey = "" )
	public void testCompositeElementMerging() {
		Session session = openSession();
		session.beginTransaction();
		Container container = new Container( "p1" );
		Container.Content c1 = new Container.Content( "c1" );
		container.getContents().add( c1 );
		session.save( container );
		session.getTransaction().commit();
		session.close();

		CollectionStatistics stats =  sessionFactory().getStatistics().getCollectionStatistics( Container.class.getName() + ".contents" );
		long recreateCount = stats.getRecreateCount();
		long updateCount = stats.getUpdateCount();

		container.setName( "another name" );

		session = openSession();
		session.beginTransaction();
		container = ( Container ) session.merge( container );
		session.getTransaction().commit();
		session.close();

		assertEquals( 1, container.getContents().size() );
		assertEquals( recreateCount, stats.getRecreateCount() );
		assertEquals( updateCount, stats.getUpdateCount() );

		session = openSession();
		session.beginTransaction();
		container = ( Container ) session.get( Container.class, container.getId() );
		assertEquals( 1, container.getContents().size() );
		session.delete( container );
		session.getTransaction().commit();
		session.close();
	}
