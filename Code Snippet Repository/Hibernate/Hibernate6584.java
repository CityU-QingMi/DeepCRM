	private void insertUser(
			Session session,
			String emailAddress,
			int age,
			boolean lockedOut,
			String username,
			String password) {
		User user = new User();
		user.setEmailAddress( emailAddress );
		user.setAge( age );
		user.setLockedOut( lockedOut );
		user.setUsername( username );
		user.setPassword( password );
		session.persist( user );
	}
