	@Override
	protected void prepareTest() {
		Session s = openSession();
		s.beginTransaction();
		user = new User( "user" );
		group = new Group( "group" );
		s.save( user );
		s.save( group );
		membership = createMembership( "membership");
		addMembership( user, group, membership );
		s.getTransaction().commit();
		s.close();
	}
