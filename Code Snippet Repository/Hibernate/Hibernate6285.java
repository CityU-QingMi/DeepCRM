	@Test
	public void testDefaultConfigurationModeIsInherited() throws Exception {
		User john = new User();
		john.setFirstname( "John" );
		john.setLastname( "Doe" );
		List<User> friends = new ArrayList<User>();
		User friend = new User();
		friend.setFirstname( "Jane" );
		friend.setLastname( "Doe" );
		friends.add( friend );
		john.setFriends( friends );

		Session s = openSession();
		s.persist( john );
		Transaction tx = s.beginTransaction();
		tx.commit();
		s.clear();
		tx = s.beginTransaction();
		john = ( User ) s.get( User.class, john.getId() );
		assertEquals( "Wrong number of friends", 1, john.getFriends().size() );
		assertNull( john.firstname );

		s.delete( john );
		tx.commit();
		s.close();
	}
