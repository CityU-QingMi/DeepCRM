	@Test
	public void testCacheSynchronizationOnMutation() {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		User u = new User( "gavin", "hb", "secret" );
		s.persist( u );
		t.commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		u = (User) s.byId( User.class ).getReference( u.getId() );
		u.setOrg( "ceylon" );
		User oldNaturalId = (User) s.byNaturalId( User.class ).using( "name", "gavin" ).using( "org", "hb" ).load();
		assertNull( oldNaturalId );
		assertNotSame( u, oldNaturalId );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		s.delete( u );
		s.getTransaction().commit();
		s.close();
	}
