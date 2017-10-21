	@Test
	@TestForIssue(jiraKey = "")
	public void testGetLimitStringWithFromInColumnName() {
		final String query = "select [Created From Nonstock Item], field2 from table1";

		assertEquals( "WITH query AS (SELECT inner_query.*, ROW_NUMBER() OVER (ORDER BY CURRENT_TIMESTAMP) as __hibernate_row_nr__ FROM ( " +
						"select [Created From Nonstock Item] as page0_, field2 as page1_ from table1 ) inner_query ) " +
						"SELECT page0_, page1_ FROM query WHERE __hibernate_row_nr__ >= ? AND __hibernate_row_nr__ < ?",
				dialect.getLimitHandler().processSql( query, toRowSelection( 1, 5 ) )
		);
	}
