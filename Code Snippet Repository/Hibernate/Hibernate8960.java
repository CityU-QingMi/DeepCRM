	@Test
	public void testMergeBidiForeignKeyOneToOne() throws Exception {
		Session s = openSession();
        s.beginTransaction();
		Person p = new Person( "steve" );
		Address a = new Address( "123 Main", "Austin", "US", p );
		s.persist( a );
		s.persist( p );
		s.getTransaction().commit();
		s.close();

		clearCounts();

		p.getAddress().setStreetAddress( "321 Main" );
		s = openSession();
        s.beginTransaction();
		p = ( Person ) s.merge( p );
		s.getTransaction().commit();
		s.close();

		assertInsertCount( 0 );
		assertUpdateCount( 0 ); // no cascade
		assertDeleteCount( 0 );

		s = openSession();
        s.beginTransaction();
		s.delete( a );
		s.delete( p );
		s.getTransaction().commit();
		s.close();
	}
