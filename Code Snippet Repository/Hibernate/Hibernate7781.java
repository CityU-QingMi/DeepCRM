	@Test
	@FailureExpected( jiraKey = "" )
	public void testWhereClauseOnUnidirectionalCollection() {
		Session s = openSession();
		Transaction t = s.beginTransaction();

		Championship championship = new Championship( 1 );
		s.persist(championship);

		Student gavin = new Student("gavin", 4);
		Student turin = new Student("turin", 3);
		Student mike = new Student("mike", 5);
		Student fred = new Student("fred", 2);

		championship.getStudents().add( gavin );
		championship.getStudents().add( turin );
		championship.getStudents().add( mike );
		championship.getStudents().add( fred );

		s.persist(gavin);
		s.persist(turin);
		s.persist(mike);
		s.persist(fred);

		t.commit();
		s.close();

		s = openSession();

		Championship championship2 = s.get(Championship.class, 1);
		assertEquals( 2, championship2.getStudents().size() );
		assertTrue( championship2.getStudents().contains( gavin ) );
		assertTrue( championship2.getStudents().contains( mike ) );

		s.close();
	}
