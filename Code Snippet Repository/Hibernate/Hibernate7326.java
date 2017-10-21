	@Test
	@TestForIssue( jiraKey = "" )
	public void testMapKeyColumnNonInsertableNonUpdatableBidirOneToMany() {
		Session s = openSession();
		s.getTransaction().begin();
		User user = new User();
		Address address = new Address();
		address.addressType = "email";
		address.addressText = "jane@doe.com";
		user.addresses.put( address.addressType, address );
		address.user = user;
		s.persist( user );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.getTransaction().begin();
		user = s.get( User.class, user.id );
		user.addresses.clear();
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.getTransaction().begin();
		user = s.get( User.class, user.id );
		s.delete( user );
		s.createQuery( "delete from " + User.class.getName() ).executeUpdate();
		s.getTransaction().commit();
		s.close();
	}
