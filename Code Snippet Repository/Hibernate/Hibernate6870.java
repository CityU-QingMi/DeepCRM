	public void testNullsFirstLastSpawnMultipleColumns() {
		Session session = openSession();

		// Populating database with test data.
		session.getTransaction().begin();
		Zoo zoo = new Zoo();
		zoo.setName( "Berlin ZOO" );
		Visitor visitor1 = new Visitor( null, null );
		Visitor visitor2 = new Visitor( null, "Antoniak" );
		Visitor visitor3 = new Visitor( "Lukasz", "Antoniak" );
		zoo.getVisitors().add( visitor1 );
		zoo.getVisitors().add( visitor2 );
		zoo.getVisitors().add( visitor3 );
		session.save( zoo );
		session.save( visitor1 );
		session.save( visitor2 );
		session.save( visitor3 );
		session.getTransaction().commit();

		session.clear();

		session.getTransaction().begin();
		zoo = (Zoo) session.get( Zoo.class, zoo.getId() );
		Iterator<Visitor> iterator = zoo.getVisitors().iterator();
		Assert.assertEquals( 3, zoo.getVisitors().size() );
		Assert.assertEquals( visitor3, iterator.next() );
		Assert.assertEquals( visitor2, iterator.next() );
		Assert.assertEquals( visitor1, iterator.next() );
		session.getTransaction().commit();

		session.clear();

		// Cleanup data.
		session.getTransaction().begin();
		session.delete( visitor1 );
		session.delete( visitor2 );
		session.delete( visitor3 );
		session.delete( zoo );
		session.getTransaction().commit();

		session.close();
	}
