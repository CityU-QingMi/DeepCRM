	@Test
	public void testFetch() {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		User u = new User( "gavin" );
		u.getPermissions().add( new Permission( "obnoxiousness" ) );
		u.getPermissions().add( new Permission( "pigheadedness" ) );
		u.getEmailAddresses().add( new Email( "gavin@hibernate.org" ) );
		u.getEmailAddresses().add( new Email( "gavin.king@jboss.com" ) );
		s.persist( u );
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		User u2 = ( User ) s.createCriteria( User.class ).uniqueResult();
		assertTrue( Hibernate.isInitialized( u2.getEmailAddresses() ) );
		assertFalse( Hibernate.isInitialized( u2.getPermissions() ) );
		assertEquals( u2.getEmailAddresses().size(), 2 );
		s.delete( u2 );
		t.commit();
		s.close();
	}
