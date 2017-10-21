	@Test
	@TestForIssue(jiraKey = "")
	public void testGetLimitStringMaxRowsOnlyNoOrderBy() {
		// this test defaults back to validating result matches that from SQLServer2005LimitHandler
		// See SQLServer2012LimitHandler for why this falls back
		final String input = "select f1 from table";
		assertEquals(
				"select top(?) f1 from table",
				dialect.getLimitHandler().processSql( input, toRowSelection( 0, 10 ) ).toLowerCase( Locale.ROOT )
		);
	}
