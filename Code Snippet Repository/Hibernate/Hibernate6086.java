	@Test
	@TestForIssue( jiraKey = "")
	public void testConflictWithSessionNativeQuery(){
		EntityManager em = getOrCreateEntityManager();
		final String sqlString = "SELECT * FROM GAME g WHERE title = ?";
		try {
			NativeQuery sqlQuery = em.unwrap( Session.class ).createSQLQuery( sqlString );
			sqlQuery.setString( 0, "Super Mario Brothers").setCacheable( true );

			List results = sqlQuery.list();
			assertEquals( 1, results.size() );

			NativeQueryImplementor query = (NativeQueryImplementor) em.createNativeQuery( sqlString );
			query.setString( 1, "Super Mario Brothers" );
			List list = query.list();
			assertEquals( 1, list.size() );

			sqlQuery = em.unwrap( Session.class ).createSQLQuery( sqlString );
			sqlQuery.setString( 0, "Super Mario Brothers").setCacheable( true );

			results = sqlQuery.list();
			assertEquals( 1, results.size() );

			query.setString( 1, "Super Mario Brothers" );
		}
		finally {
			em.close();
		}
	}
