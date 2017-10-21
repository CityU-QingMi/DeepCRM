	@Test
	public void testExtraLazy() throws HibernateException, SQLException {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		User u = new User( "gavin" );
		u.getPermissions().add( new Permission( "obnoxiousness" ) );
		u.getPermissions().add( new Permission( "pigheadedness" ) );
		u.getSessionData().put( "foo", "foo value" );
		s.persist( u );
		t.commit();
		s.close();
		s = openSession();
		t = s.beginTransaction();
		u = ( User ) s.get( User.class, "gavin" );

		assertFalse( Hibernate.isInitialized( u.getPermissions() ) );
		assertEquals( u.getPermissions().size(), 2 );
		assertTrue( u.getPermissions().contains( new Permission( "obnoxiousness" ) ) );
		assertFalse( u.getPermissions().contains( new Permission( "silliness" ) ) );
		assertNotNull( u.getPermissions().get( 1 ) );
		assertNull( u.getPermissions().get( 3 ) );
		assertFalse( Hibernate.isInitialized( u.getPermissions() ) );

		assertFalse( Hibernate.isInitialized( u.getSessionData() ) );
		assertEquals( u.getSessionData().size(), 1 );
		assertTrue( u.getSessionData().containsKey( "foo" ) );
		assertFalse( u.getSessionData().containsKey( "bar" ) );
		assertTrue( u.getSessionData().containsValue( "foo value" ) );
		assertFalse( u.getSessionData().containsValue( "bar" ) );
		assertEquals( "foo value", u.getSessionData().get( "foo" ) );
		assertNull( u.getSessionData().get( "bar" ) );
		assertFalse( Hibernate.isInitialized( u.getSessionData() ) );

		assertFalse( Hibernate.isInitialized( u.getSessionData() ) );
		u.getSessionData().put( "bar", "bar value" );
		u.getSessionAttributeNames().add( "bar" );
		assertFalse( Hibernate.isInitialized( u.getSessionAttributeNames() ) );
		assertTrue( Hibernate.isInitialized( u.getSessionData() ) );

		s.delete( u );
		t.commit();
		s.close();
	}
