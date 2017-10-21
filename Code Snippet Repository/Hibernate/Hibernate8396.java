	@Test
	public void testJoinFetchManyToMany() {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		Group hb = new Group("hibernate");
		User gavin = new User("gavin");
		User max = new User("max");
		hb.getUsers().put("gavin", gavin);
		hb.getUsers().put("max", max);
		gavin.getGroups().put("hibernate", hb);
		max.getGroups().put("hibernate", hb);
		s.persist(hb);
		t.commit();
		s.close();
		
		s = openSession();
		t = s.beginTransaction();
		hb = s.get(Group.class, "hibernate");
		assertTrue( Hibernate.isInitialized( hb.getUsers() ) );
		gavin = (User) hb.getUsers().get("gavin");
		assertFalse( Hibernate.isInitialized( gavin.getGroups() ) );
		max = s.get(User.class, "max");
		assertFalse( Hibernate.isInitialized( max.getGroups() ) );
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		hb = (Group) s.createCriteria(Group.class)
			.setFetchMode("users", FetchMode.JOIN)
			.setFetchMode("users.groups", FetchMode.JOIN)
			.uniqueResult();
		assertTrue( Hibernate.isInitialized( hb.getUsers() ) );
		gavin = (User) hb.getUsers().get("gavin");
		assertTrue( Hibernate.isInitialized( gavin.getGroups() ) );
		max = s.get(User.class, "max");
		assertTrue( Hibernate.isInitialized( max.getGroups() ) );
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		s.delete(hb);
		t.commit();
		s.close();
	}
