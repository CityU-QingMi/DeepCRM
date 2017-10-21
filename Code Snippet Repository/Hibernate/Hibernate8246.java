	@Test
	@TestForIssue( jiraKey = "")
	public void testH2() {
		StandardServiceRegistry ssr = buildStandardServiceRegistry( H2Dialect.class );
		try {
			checkUuidTypeUsed( ssr, UUIDBinaryType.class );
		}
		finally {
			StandardServiceRegistryBuilder.destroy( ssr );
		}
	}
