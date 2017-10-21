	@Test
	public void testSimpleNaturalIdLoadAccessCache() {
		Session s = openSession();
		s.beginTransaction();
		User u = new User( "steve", "superSecret" );
		s.persist( u );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		u = (User) s.bySimpleNaturalId( User.class ).load( "steve" );
		assertNotNull( u );
		User u2 = (User) s.bySimpleNaturalId( User.class ).getReference( "steve" );
		assertTrue( u == u2 );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		s.createQuery( "delete User" ).executeUpdate();
		s.getTransaction().commit();
		s.close();
	}
