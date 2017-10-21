	@Test
	@TestForIssue(jiraKey = "")
	public void testGetLimitStringAliasGenerationWithAliasesNoAs() {
		final String aliasedSQLNoAs = "select column1 c1, column c2, column c3, column c4 from table1";
		assertEquals(
				"WITH query AS (SELECT inner_query.*, ROW_NUMBER() OVER (ORDER BY CURRENT_TIMESTAMP) as __hibernate_row_nr__ FROM ( " +
						"select column1 c1, column c2, column c3, column c4 from table1 ) inner_query ) " +
						"SELECT c1, c2, c3, c4 FROM query WHERE __hibernate_row_nr__ >= ? AND __hibernate_row_nr__ < ?",
				dialect.getLimitHandler().processSql( aliasedSQLNoAs, toRowSelection( 3, 5 ) )
		);
	}
