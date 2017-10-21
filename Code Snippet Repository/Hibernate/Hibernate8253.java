	@Test
	public void testUpdateIdBag() throws HibernateException, SQLException {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		User gavin = new User("gavin");
		Group admins = new Group("admins");
		Group plebs = new Group("plebs");
		Group moderators = new Group("moderators");
		Group banned = new Group("banned");
		gavin.getGroups().add(plebs);
		//gavin.getGroups().add(moderators);
		s.persist(gavin);
		s.persist(plebs);
		s.persist(admins);
		s.persist(moderators);
		s.persist(banned);
		t.commit();
		s.close();
		
		s = openSession();
		t = s.beginTransaction();
		gavin = (User) s.createCriteria(User.class).uniqueResult();
		admins = (Group) s.load(Group.class, "admins");
		plebs = (Group) s.load(Group.class, "plebs");
		banned = (Group) s.load(Group.class, "banned");
		gavin.getGroups().add(admins);
		gavin.getGroups().remove(plebs);
		//gavin.getGroups().add(banned);

		s.delete(plebs);
		s.delete(banned);
		s.delete(moderators);
		s.delete(admins);
		s.delete(gavin);
		
		t.commit();
		s.close();		
	}
