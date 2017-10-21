	@Test
	public void testBasics() {
		Session s = openSession();
		s.beginTransaction();
		Entity entity = new Entity( "BigInteger + sequence #1" );
		s.save( entity );
		Entity entity2 = new Entity( "BigInteger + sequence #2" );
		s.save( entity2 );
		s.getTransaction().commit();
		s.close();

// hsqldb defines different behavior for the initial select from a sequence
// then say oracle
//		assertEquals( BigInteger.valueOf( 1 ), entity.getId() );
//		assertEquals( BigInteger.valueOf( 2 ), entity2.getId() );

		s = openSession();
		s.beginTransaction();
		s.delete( entity );
		s.delete( entity2 );
		s.getTransaction().commit();
		s.close();

	}
