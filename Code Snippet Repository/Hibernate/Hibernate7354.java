	@Test
	public void testUpdateOrder() {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		User u = new User( "gavin" );
		u.getSessionData().put( "foo", "foo value" );
		u.getSessionData().put( "bar", "bar value" );
		u.getEmailAddresses().add( new Email( "gavin.king@jboss.com" ) );
		u.getEmailAddresses().add( new Email( "gavin@hibernate.org" ) );
		u.getEmailAddresses().add( new Email( "gavin@illflow.com" ) );
		u.getEmailAddresses().add( new Email( "gavin@nospam.com" ) );
		s.persist( u );
		t.commit();
		s.close();

		u.getSessionData().clear();
		u.getSessionData().put( "baz", "baz value" );
		u.getSessionData().put( "bar", "bar value" );
		u.getEmailAddresses().remove( 0 );
		u.getEmailAddresses().remove( 2 );

		s = openSession();
		t = s.beginTransaction();
		s.update( u );
		t.commit();
		s.close();

		u.getSessionData().clear();
		u.getEmailAddresses().add( 0, new Email( "gavin@nospam.com" ) );
		u.getEmailAddresses().add( new Email( "gavin.king@jboss.com" ) );

		s = openSession();
		t = s.beginTransaction();
		s.update( u );
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		s.delete( u );
		t.commit();
		s.close();

	}
