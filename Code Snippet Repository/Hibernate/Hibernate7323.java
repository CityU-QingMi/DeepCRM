	@Test
	@TestForIssue(jiraKey = "")
	public void testClearMap() {
		Session s = openSession();
		s.beginTransaction();
		
		User user = new User();
		UserData userData = new UserData();
		userData.user = user;
		user.userDatas.put( "foo", userData );
		s.persist( user );
		
		s.getTransaction().commit();
		s.clear();
		
		s.beginTransaction();
		
		user = s.get( User.class, 1 );
	    user.userDatas.clear();
	    s.update( user );
		Query q = s.createQuery( "DELETE FROM " + UserData.class.getName() + " d WHERE d.user = :user" );
		q.setParameter( "user", user );
		q.executeUpdate();
		
		s.getTransaction().commit();

		s.getTransaction().begin();

		assertEquals( s.get( User.class, user.id ).userDatas.size(), 0 );
		assertEquals( s.createQuery( "FROM " + UserData.class.getName() ).list().size(), 0 );
		s.createQuery( "delete " + User.class.getName() ).executeUpdate();

		s.getTransaction().commit();
		s.close();
	}
