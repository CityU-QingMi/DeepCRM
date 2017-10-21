	@Test
	@SuppressWarnings( {""})
	public void testManyToManyFormula() {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		User gavin = new User("gavin", "secret");
		User turin = new User("turin", "tiger");
		Group g = new Group("users");
		g.getUsers().put("Gavin", gavin);
		g.getUsers().put("Turin", turin);
		s.persist(g);
		t.commit();
		s.close();
		
		s = openSession();
		t = s.beginTransaction();
		g = (Group) s.get(Group.class, "users");
		assertEquals( g.getUsers().size(), 2 );
		g.getUsers().remove("Turin");
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		g = (Group) s.get(Group.class, "users");
		assertEquals( g.getUsers().size(), 1 );
		s.delete(g);
		s.delete( g.getUsers().get("Gavin") );
		s.delete( s.get(User.class, "turin") );
		t.commit();
		s.close();
	}
