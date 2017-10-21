	@Test
	@TestForIssue(jiraKey = "")
	public void testGetLimitStringWithOffsetAndMaxRowsNoOrderBy() {
		// this test defaults back to validating result matches that from SQLServer2005LimitHandler
		// See SQLServer2012LimitHandler for why this falls back
		final String input = "select f1 from table";
		assertEquals(
				"with query as (select inner_query.*, row_number() over (order by current_timestamp) as __hibernate_row_nr__ " +
						"from ( select f1 as page0_ from table ) inner_query ) select page0_ from query where " +
						"__hibernate_row_nr__ >= ? and __hibernate_row_nr__ < ?",
				dialect.getLimitHandler().processSql( input, toRowSelection( 5, 10 ) ).toLowerCase( Locale.ROOT )
		);
	}
