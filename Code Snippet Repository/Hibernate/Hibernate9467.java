	@Test
	@TestForIssue( jiraKey = "" )
	public void testStatementClosing() {
		Session session = openSession();
		session.getTransaction().begin();
		// Reading maximum number of opened cursors requires SYS privileges.
		// Verify statement closing with JdbcCoordinator#hasRegisteredResources() instead.
		// BigDecimal maxCursors = (BigDecimal) session.createSQLQuery( "SELECT value FROM v$parameter WHERE name = 'open_cursors'" ).uniqueResult();
		// for ( int i = 0; i < maxCursors + 10; ++i ) { named_query_execution }
		Assert.assertEquals(
				Arrays.asList( new NumValue( 1, "Line 1" ), new NumValue( 2, "Line 2" ) ),
				session.getNamedQuery( "NumValue.getSomeValues" ).list()
		);
		JdbcCoordinator jdbcCoordinator = ( (SessionImplementor) session ).getJdbcCoordinator();
		Assert.assertFalse(
				"Prepared statement and result set should be released after query execution.",
				jdbcCoordinator.getResourceRegistry().hasRegisteredResources()
		);
		session.getTransaction().commit();
		session.close();
	}
