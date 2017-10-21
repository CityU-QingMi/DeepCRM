	@Test
	@SuppressWarnings( {""})
	public void testJoinFetchedCollectionOfJoinedSubclass() throws Exception {
		Mammal mammal = new Mammal();
		mammal.setDescription( "A Zebra" );
		Zoo zoo = new Zoo();
		zoo.setName( "A Zoo" );
		zoo.getMammals().put( "zebra", mammal );
		mammal.setZoo( zoo );

		Session session = openSession();
		Transaction txn = session.beginTransaction();
		session.save( mammal );
		session.save( zoo );
		txn.commit();
		session.close();

		session = openSession();
		txn = session.beginTransaction();
		List results = session.createQuery( "from Zoo z join fetch z.mammals" ).list();
		assertEquals( "Incorrect result size", 1, results.size() );
		assertTrue( "Incorrect result return type", results.get( 0 ) instanceof Zoo );
		Zoo zooRead = ( Zoo ) results.get( 0 );
		assertEquals( zoo, zooRead );
		assertTrue( Hibernate.isInitialized( zooRead.getMammals() ) );
		Mammal mammalRead = ( Mammal ) zooRead.getMammals().get( "zebra" );
		assertEquals( mammal, mammalRead );
		session.delete( mammalRead );
		session.delete( zooRead );
		txn.commit();
		session.close();
	}
