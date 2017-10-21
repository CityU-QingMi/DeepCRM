	@Test
	@TestForIssue(jiraKey = "")
	public void testGetLimitWithStringValueContainingParenthesis() {
		final String query = "select t1.c1 as col_0_0 FROM table1 t1 where t1.c1 = '(123' ORDER BY t1.c1 ASC";

		assertEquals(
				"WITH query AS (SELECT inner_query.*, ROW_NUMBER() OVER (ORDER BY CURRENT_TIMESTAMP) as __hibernate_row_nr__ FROM ( " +
						"select TOP(?) t1.c1 as col_0_0 FROM table1 t1 where t1.c1 = '(123' ORDER BY t1.c1 ASC ) inner_query ) SELECT col_0_0 FROM query WHERE __hibernate_row_nr__ >= ? AND __hibernate_row_nr__ < ?",
				dialect.getLimitHandler().processSql( query, toRowSelection( 1, 5 ) )
		);
	}
