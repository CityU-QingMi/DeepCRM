	@Test
	@TestForIssue( jiraKey = "" )
	public void testDistinctPassThroughTrue() {
		doInHibernate( this::sessionFactory, session -> {
			sqlStatementInterceptor.getSqlQueries().clear();
			List<Person> persons = session.createQuery(
					"select distinct p from Person p left join fetch p.phones ")
					.setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, true)
					.setMaxResults(5)
					.getResultList();
			assertEquals(1, persons.size());
			String sqlQuery = sqlStatementInterceptor.getSqlQueries().getLast();
			assertTrue(sqlQuery.contains(" distinct "));
		});
	}
