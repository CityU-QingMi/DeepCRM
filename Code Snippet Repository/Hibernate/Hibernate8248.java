	@Test
	@TestForIssue( jiraKey = "")
	public void testPostgreSQL() {
		StandardServiceRegistry ssr = buildStandardServiceRegistry( PostgreSQL94Dialect.class );
		try {
			checkUuidTypeUsed( ssr, PostgresUUIDType.class );
		}
		finally {
			StandardServiceRegistryBuilder.destroy( ssr );
		}
	}
