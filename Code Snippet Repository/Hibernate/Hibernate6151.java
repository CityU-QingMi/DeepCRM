	@SuppressWarnings("")
	@Test
	@TestForIssue(jiraKey = "")
	public void testSqlDropScriptSourceUrl() throws Exception {
		Map settings = buildSettings();
		settings.put( AvailableSettings.HBM2DDL_DROP_SOURCE, "metadata-then-script" );
		settings.put( AvailableSettings.HBM2DDL_DATABASE_ACTION, "drop" );
		settings.put( AvailableSettings.HBM2DDL_DROP_SCRIPT_SOURCE, getResourceUrlString( getDropSqlScript() ) );
		doTest( settings );
	}
