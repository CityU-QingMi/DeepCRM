	@Test
	@TestForIssue(jiraKey = "")
	public void testGetLimitStringAliasGeneration() {
		final String notAliasedSQL = "select column1, column2, column3, column4 from table1";

		assertEquals(
				"WITH query AS (SELECT inner_query.*, ROW_NUMBER() OVER (ORDER BY CURRENT_TIMESTAMP) as __hibernate_row_nr__ FROM ( " +
						"select column1 as page0_, column2 as page1_, column3 as page2_, column4 as page3_ from table1 ) inner_query ) " +
						"SELECT page0_, page1_, page2_, page3_ FROM query WHERE __hibernate_row_nr__ >= ? AND __hibernate_row_nr__ < ?",
				dialect.getLimitHandler().processSql( notAliasedSQL, toRowSelection( 3, 5 ) )
		);
	}
