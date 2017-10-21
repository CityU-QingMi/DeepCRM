	@Test
	public void testRemoveClear() {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		User gavin = new User("gavin", "secret");
		User turin = new User("turin", "tiger");
		Group g = new Group("developers");
		g.getUsers().put("gavin", gavin);
		g.getUsers().put("turin", turin);
		s.persist(g);
		gavin.getSession().put( "foo", new SessionAttribute("foo", "foo bar baz") );
		gavin.getSession().put( "bar", new SessionAttribute("bar", "foo bar baz 2") );
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		g = (Group) s.get(Group.class, "developers");
		gavin = (User) g.getUsers().get("gavin");
		turin = (User) g.getUsers().get("turin");
		assertFalse( Hibernate.isInitialized( g.getUsers() ) );
		g.getUsers().clear();
		gavin.getSession().remove("foo");
		assertTrue( Hibernate.isInitialized( g.getUsers() ) );
		assertTrue( Hibernate.isInitialized( gavin.getSession() ) );
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		g = (Group) s.get(Group.class, "developers");
		assertTrue( g.getUsers().isEmpty() );
		assertFalse( Hibernate.isInitialized( g.getUsers() ) );
		gavin = (User) s.get(User.class, "gavin");
		assertFalse( gavin.getSession().containsKey("foo") );
		assertFalse( Hibernate.isInitialized( gavin.getSession() ) );
		s.delete(gavin);
		s.delete(turin);
		s.delete(g);
		t.commit();
		s.close();
	}
