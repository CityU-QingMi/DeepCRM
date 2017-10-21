	protected void cleanupTestData() {
		// clean up the test data
		Session s = openSession();
		s.beginTransaction();
		// User is the non-inverse side...
		List<User> users = s.createQuery( "from User" ).list();
		for ( User user : users ) {
			s.delete( user );
		}
		s.flush();
		s.createQuery( "delete Group" ).executeUpdate();
		s.getTransaction().commit();
		s.close();
	}
