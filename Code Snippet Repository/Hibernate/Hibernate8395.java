	@Test
	public void testCollectionFilter() {
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
		hb = (Group) s.createCriteria(Group.class)
				.setFetchMode("users", FetchMode.SELECT)
				.add( Restrictions.idEq("hibernate") )
				.uniqueResult();
		assertFalse( Hibernate.isInitialized( hb.getUsers() ) );
		//gavin = (User) s.createFilter( hb.getUsers(), "where index(this) = 'gavin'" ).uniqueResult();
		Long size = (Long) s.createFilter( hb.getUsers(), "select count(*)" ).uniqueResult();
		assertEquals( new Long(2), size );
		assertFalse( Hibernate.isInitialized( hb.getUsers() ) );
		s.delete(hb);
		t.commit();
		s.close();
		
	}
