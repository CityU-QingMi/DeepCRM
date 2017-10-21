	@Test
	@TestForIssue(jiraKey = "")
	public void testDoubleQuoteJoinColumn() {
		Session s = openSession();
		s.getTransaction().begin();
		User user = new User();
		House house = new House();
		user.setHouse( house );
		s.persist( house );
		s.persist( user );
		s.getTransaction().commit();
		s.close();
		
		s = openSession();
		s.getTransaction().begin();
		user = s.get( User.class, user.getId() );
		assertNotNull( user );
		assertNotNull( user.getHouse() );
		// seems trivial, but if quoting normalization worked on the join column, these should all be the same
		assertEquals( user.getHouse().getId(), user.getHouse1() );
		assertEquals( user.getHouse().getId(), user.getHouse2() );
		s.getTransaction().commit();
		s.close();
	}
