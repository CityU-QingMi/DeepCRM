	@Test
	public void testUpdateWithNullRelation() {
		Session session = openSession();
		session.beginTransaction();
		User user = new User();
		user.setName( "User1" );
		session.persist( user );

		session.getTransaction().commit();
		session.close();

		session = openSession();
		session.beginTransaction();
		user.setName( "UserUpdate" );
		session.merge( user );

		session.getTransaction().commit();
		session.close();
	}
