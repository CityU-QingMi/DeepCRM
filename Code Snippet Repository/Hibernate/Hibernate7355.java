	@Test
	public void testValueMap() {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		User u = new User( "gavin" );
		u.getSessionData().put( "foo", "foo value" );
		u.getSessionData().put( "bar", null );
		u.getEmailAddresses().add( null );
		u.getEmailAddresses().add( new Email( "gavin.king@jboss.com" ) );
		u.getEmailAddresses().add( null );
		u.getEmailAddresses().add( null );
		s.persist( u );
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		User u2 = ( User ) s.createCriteria( User.class ).uniqueResult();
		assertFalse( Hibernate.isInitialized( u2.getSessionData() ) );
		assertEquals( u2.getSessionData().size(), 1 );
		assertEquals( u2.getEmailAddresses().size(), 2 );
		u2.getSessionData().put( "foo", "new foo value" );
		u2.getEmailAddresses().set( 1, new Email( "gavin@hibernate.org" ) );
		//u2.getEmailAddresses().remove(3);
		//u2.getEmailAddresses().remove(2);
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		u2 = ( User ) s.createCriteria( User.class ).uniqueResult();
		assertFalse( Hibernate.isInitialized( u2.getSessionData() ) );
		assertEquals( u2.getSessionData().size(), 1 );
		assertEquals( u2.getEmailAddresses().size(), 2 );
		assertEquals( u2.getSessionData().get( "foo" ), "new foo value" );
		assertEquals( ( ( Email ) u2.getEmailAddresses().get( 1 ) ).getAddress(), "gavin@hibernate.org" );
		s.delete( u2 );
		t.commit();
		s.close();
	}
