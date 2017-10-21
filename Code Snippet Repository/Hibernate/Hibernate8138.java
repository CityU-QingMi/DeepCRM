	@Test
	public void testQueryWithEntityNameAsStringLiteral() {
		final String qry = "select e from Employee a where e.name = ', Employee Number 1'";

		String[] results = QuerySplitter.concreteQueries( qry, sessionFactory() );
		assertEquals( 1, results.length );
		assertEquals(
				"select e from org.hibernate.test.hql.QuerySplitterTest$Employee a where e.name = ', Employee Number 1'",
				results[0]
		);
	}
