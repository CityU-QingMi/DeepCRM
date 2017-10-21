	@Test
	public void testJoin() throws HibernateException, SQLException {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		User gavin = new User("gavin");
		Group admins = new Group("admins");
		Group plebs = new Group("plebs");
		gavin.getGroups().add(plebs);
		gavin.getGroups().add(admins);
		s.persist(gavin);
		s.persist(plebs);
		s.persist(admins);
		
		List l = s.createQuery("from User u join u.groups g").list();
		assertEquals( l.size(), 2 );
		s.clear();
		
		gavin = (User) s.createQuery("from User u join fetch u.groups").uniqueResult();
		assertTrue( Hibernate.isInitialized( gavin.getGroups() ) );
		assertEquals( gavin.getGroups().size(), 2 );
		assertEquals( ( (Group) gavin.getGroups().get(0) ).getName(), "admins" );
		
		s.delete( gavin.getGroups().get(0) );
		s.delete( gavin.getGroups().get(1) );
		s.delete(gavin);
		
		t.commit();
		s.close();		
	}
