	@Test
	public void testSubclassModifieablNaturalId() {
		Session s = openSession();
		s.beginTransaction();
		s.save( new User( "steve" ) );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		Principal p = (Principal) s.bySimpleNaturalId( Principal.class ).load( "steve" );
		assertNotNull( p );
		User u = (User) s.bySimpleNaturalId( User.class ).load( "steve" );
		assertNotNull( u );
		assertSame( p, u );

		// change the natural id
		u.setUid( "sebersole" );
		s.flush();

		// make sure we can no longer access the info based on the old natural id value
		assertNull( s.bySimpleNaturalId( Principal.class ).load( "steve" ) );
		assertNull( s.bySimpleNaturalId( User.class ).load( "steve" ) );

		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		s.delete( u );
		s.getTransaction().commit();
		s.close();
	}
