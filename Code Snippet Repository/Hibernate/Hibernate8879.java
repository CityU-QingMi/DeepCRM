	@Test
	public void testEviction() {
		Session s = openSession();
		s.beginTransaction();
		User u = new User( "steve", "hb", "superSecret" );
		s.persist( u );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		u = (User) session.byNaturalId( User.class )
				.using( "name", "steve" )
				.using( "org", "hb" )
				.load();
		assertNotNull( u );
		s.evict( u );
		u = (User) session.byNaturalId( User.class )
				.using( "name", "steve" )
				.using( "org", "hb" )
				.load();
		assertNotNull( u );
		s.delete( u );
		s.getTransaction().commit();
		s.close();
	}
