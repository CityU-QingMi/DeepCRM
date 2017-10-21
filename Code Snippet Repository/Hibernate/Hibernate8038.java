	@Test
	@TestForIssue(jiraKey = "")
	public void testSelectNewCastNull() {
		Session s = openSession();
		s.getTransaction().begin();
		Person person = new Person();
		person.firstName = "Herman";
		person.middleName = "Joseph";
		person.lastName = "Munster";
		s.persist( person );
		s.flush();
		s.clear();

		Person result = (Person) s.createQuery(
				"select new CastNullSelectExpressionTest$Person( id, firstName, cast( null as string ), lastName ) from CastNullSelectExpressionTest$Person where lastName='Munster'"
		).uniqueResult();
		assertEquals( "Herman", result.firstName );
		assertNull( result.middleName );
		assertEquals( "Munster", result.lastName );

		s.getTransaction().rollback();
		s.close();
	}
