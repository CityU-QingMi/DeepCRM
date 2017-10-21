	@org.junit.Test
	public void basicTest() {
		Session s = openSession();
		s.beginTransaction();
		User user = new User( 2 );
		s.save( user );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		assertNotNull( s.get( User.class, 2 ) );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		s.createQuery( "delete User" ).executeUpdate();
		s.getTransaction().commit();
		s.close();
	}
