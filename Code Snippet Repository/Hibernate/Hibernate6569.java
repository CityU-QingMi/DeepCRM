	@Test
	public void testMapKeyEnumerated() {
		Session s = openSession();
		s.beginTransaction();
		User user = new User(SocialNetwork.STUB_NETWORK_NAME, "facebookId");
		s.save( user );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		user = (User) s.get( User.class, user.getId() );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		user = (User) s.get( User.class, user.getId() );
		s.delete( user );
		s.getTransaction().commit();
		s.close();
	}
