	@Test @TestForIssue(jiraKey="")
	public void testIsolationPropertyCouldBeEmpty() {
		C3P0ConnectionProvider provider = new C3P0ConnectionProvider();
		try {
			Properties configuration = new Properties();
			configuration.setProperty( Environment.ISOLATION, "" );
			provider.configure( configuration );
		}
		finally {
			provider.stop();
		}
	}
