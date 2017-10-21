	@Test
	@TestForIssue(jiraKey = "")
	public void testWhereClauseOnBidirectionalCollection() {
		Session s = openSession();
		Transaction t = s.beginTransaction();

		School school = new School(1);
		s.persist(school);

		Student gavin = new Student("gavin", 4);
		Student turin = new Student("turin", 3);
		Student mike = new Student("mike", 5);
		Student fred = new Student("fred", 2);

		gavin.setSchool(school);
		turin.setSchool(school);
		mike.setSchool(school);
		fred.setSchool(school);

		s.persist(gavin);
		s.persist(turin);
		s.persist(mike);
		s.persist(fred);

		t.commit();
		s.close();

		s = openSession();
		School school2 = s.get(School.class, 1);

		assertEquals(4, school2.getStudents().size());

		assertEquals( 2, school2.getTopStudents().size() );
		assertTrue( school2.getTopStudents().contains( gavin ) );
		assertTrue( school2.getTopStudents().contains( mike ) );

		assertEquals(2,  school2.getStudentsMap().size() );
		assertTrue( school2.getStudentsMap().containsKey( gavin.getId() ) );
		assertTrue( school2.getStudentsMap().containsKey( mike.getId() ) );

		s.close();
	}
