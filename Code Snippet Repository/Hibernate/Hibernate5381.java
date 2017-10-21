	@Test
	@TestForIssue(jiraKey = "")
	public void testGetLimitStringWithQuotedColumnNamesAndAliasWithAs() {
		final String query = "select [Created From Item] as c1, field2 from table1";

		assertEquals( "WITH query AS (SELECT inner_query.*, ROW_NUMBER() OVER (ORDER BY CURRENT_TIMESTAMP) as __hibernate_row_nr__ FROM ( " +
						"select [Created From Item] as c1, field2 as page0_ from table1 ) inner_query ) " +
						"SELECT c1, page0_ FROM query WHERE __hibernate_row_nr__ >= ? AND __hibernate_row_nr__ < ?",
				dialect.getLimitHandler().processSql( query, toRowSelection( 1, 5 ) )
		);
	}
