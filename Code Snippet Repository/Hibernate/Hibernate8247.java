	@Test
	@TestForIssue( jiraKey = "")
	public void testMySQL() {
		StandardServiceRegistry ssr = buildStandardServiceRegistry( MySQL5Dialect.class );
		try {
			checkUuidTypeUsed( ssr, UUIDBinaryType.class );
		}
		finally {
			StandardServiceRegistryBuilder.destroy( ssr );
		}
	}
