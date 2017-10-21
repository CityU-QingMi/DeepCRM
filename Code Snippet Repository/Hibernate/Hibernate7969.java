	@Test
	@SuppressWarnings( {""})
	public void testJoinedCollectionOfJoinedSubclass() throws Exception {
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
		List results = session.createQuery( "from Zoo z join z.mammals m" ).list();
		assertEquals( "Incorrect result size", 1, results.size() );
		assertTrue( "Incorrect result return type", results.get( 0 ) instanceof Object[] );
		Object[] resultObjects = ( Object[] ) results.get( 0 );
		Zoo zooRead = ( Zoo ) resultObjects[ 0 ];
		Mammal mammalRead = ( Mammal ) resultObjects[ 1 ];
		assertEquals( zoo, zooRead );
		assertEquals( mammal, mammalRead );
		session.delete( mammalRead );
		session.delete( zooRead );
		txn.commit();
		session.close();
	}
