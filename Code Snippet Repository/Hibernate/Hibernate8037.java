	@Test
	@TestForIssue(jiraKey = "")
	public void testSelectCastNull() {
		Session s = openSession();
		s.getTransaction().begin();
		Person person = new Person();
		person.firstName = "Herman";
		person.middleName = "Joseph";
		person.lastName = "Munster";
		s.persist( person );
		s.flush();
		s.clear();

		Object[] result = (Object[]) s.createQuery(
				"select firstName, cast( null as string ), lastName from CastNullSelectExpressionTest$Person where lastName='Munster'"
		).uniqueResult();

		assertEquals( 3, result.length );
		assertEquals( "Herman", result[0] );
		assertNull( result[1] );
		assertEquals( "Munster", result[2] );

		s.getTransaction().rollback();
		s.close();
	}
