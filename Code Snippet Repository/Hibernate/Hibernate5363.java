	@Test
	@TestForIssue( jiraKey = "")
	public void testDefaultBatchVersionDataProperty(){
		Oracle8iDialect oracle8iDialect = new Oracle8iDialect();
		assertEquals( "false", oracle8iDialect.getDefaultProperties().getProperty( Environment.BATCH_VERSIONED_DATA ) );

		OracleDialect oracleDialect = new OracleDialect();
		assertEquals( "false", oracleDialect.getDefaultProperties().getProperty( Environment.BATCH_VERSIONED_DATA ) );

		Oracle10gDialect oracle10gDialect = new Oracle10gDialect();
		assertEquals( "false", oracle10gDialect.getDefaultProperties().getProperty( Environment.BATCH_VERSIONED_DATA ) );

		Oracle9iDialect oracle9iDialect = new Oracle9iDialect();
		assertEquals( "false", oracle9iDialect.getDefaultProperties().getProperty( Environment.BATCH_VERSIONED_DATA ) );

		Oracle9Dialect oracle9Dialect = new Oracle9Dialect();
		assertEquals( "false", oracle9Dialect.getDefaultProperties().getProperty( Environment.BATCH_VERSIONED_DATA ) );

		Oracle12cDialect oracle12cDialect = new Oracle12cDialect();
		assertEquals( "true", oracle12cDialect.getDefaultProperties().getProperty( Environment.BATCH_VERSIONED_DATA ) );
	}
