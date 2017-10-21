	@Test
	@TestForIssue(jiraKey = "" )
	public void testQueryWithEntityNameAsStringLiteral2() {
		final String qry = "from Employee where name = 'He is the, Employee Number 1'";

		String[] results = QuerySplitter.concreteQueries( qry, sessionFactory() );
		assertEquals( 1, results.length );
		assertEquals(
				"from org.hibernate.test.hql.QuerySplitterTest$Employee where name = 'He is the, Employee Number 1'",
				results[0]
		);
	}
