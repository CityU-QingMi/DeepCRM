	@SuppressWarnings("")
	@Test
	@TestForIssue(jiraKey = "")
	public void testSqlCreateScriptSourceUrl() throws Exception {
		Map settings = buildSettings();
		settings.put( AvailableSettings.HBM2DDL_DATABASE_ACTION, "drop-and-create" );
		settings.put( AvailableSettings.HBM2DDL_CREATE_SOURCE, "metadata-then-script" );
		settings.put( AvailableSettings.HBM2DDL_CREATE_SCRIPT_SOURCE, getResourceUrlString( getCreateSqlScript() ) );
		doTest( settings );
	}
