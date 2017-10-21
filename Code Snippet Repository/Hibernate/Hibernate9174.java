	@Test
	public void testQuoteManytoMany() {
		String role = User.class.getName() + ".roles";
		assertEquals( "User_Role", metadata().getCollectionBinding( role ).getCollectionTable().getName() );

		Session s = openSession();
		s.beginTransaction();
		User u = new User();
		s.persist( u );
		Role r = new Role();
		s.persist( r );
		u.getRoles().add( r );
		s.flush();
		s.clear();
		u = s.get( User.class, u.getId() );
		assertEquals( 1, u.getRoles().size() );
		s.getTransaction().rollback();
		s.close();
	}
