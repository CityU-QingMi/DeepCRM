	private void assertOrdering(List users) {
		User user = extractUser( users, 0 );
		assertTrue( "many-to-many ordering not applied", user.getName().equals( "emmanuel" ) );
		user = extractUser( users, 1 );
		assertTrue( "many-to-many ordering not applied", user.getName().equals( "gavin" ) );
		user = extractUser( users, 2 );
		assertTrue( "many-to-many ordering not applied", user.getName().equals( "max" ) );
		user = extractUser( users, 3 );
		assertTrue( "many-to-many ordering not applied", user.getName().equals( "steve" ) );
	}
