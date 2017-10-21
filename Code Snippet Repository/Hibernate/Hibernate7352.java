	@Test
	public void testMerge() throws HibernateException, SQLException {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		User u = new User( "gavin" );
		u.getPermissions().add( new Permission( "obnoxiousness" ) );
		u.getPermissions().add( new Permission( "pigheadedness" ) );
		s.persist( u );
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		User u2 = ( User ) s.createCriteria( User.class ).uniqueResult();
		u2.setPermissions( null ); //forces one shot delete
		s.merge( u );
		t.commit();
		s.close();

		u.getPermissions().add( new Permission( "silliness" ) );

		s = openSession();
		t = s.beginTransaction();
		s.merge( u );
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		u2 = ( User ) s.createCriteria( User.class ).uniqueResult();
		assertEquals( u2.getPermissions().size(), 3 );
		assertEquals( ( ( Permission ) u2.getPermissions().get( 0 ) ).getType(), "obnoxiousness" );
		assertEquals( ( ( Permission ) u2.getPermissions().get( 2 ) ).getType(), "silliness" );
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		s.delete( u2 );
		s.flush();
		t.commit();
		s.close();

	}
