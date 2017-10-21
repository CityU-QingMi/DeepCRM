	@Test
	@TestForIssue(jiraKey = "")
	public void testGetLimitStringWithSelectClauseNestedQueryUsingParenthesis() {
		final String query = "select t1.c1 as col_0_0, (select case when count(t2.c1)>0 then 'ADDED' else 'UNMODIFIED' end from table2 t2 WHERE (t2.c1 in (?))) as col_1_0 from table1 t1 WHERE 1=1 ORDER BY t1.c1 ASC";

		assertEquals(
				"WITH query AS (SELECT inner_query.*, ROW_NUMBER() OVER (ORDER BY CURRENT_TIMESTAMP) as __hibernate_row_nr__ FROM ( " +
						"select TOP(?) t1.c1 as col_0_0, (select case when count(t2.c1)>0 then 'ADDED' else 'UNMODIFIED' end from table2 t2 WHERE (t2.c1 in (?))) as col_1_0 from table1 t1 WHERE 1=1 ORDER BY t1.c1 ASC ) inner_query ) " +
						"SELECT col_0_0, col_1_0 FROM query WHERE __hibernate_row_nr__ >= ? AND __hibernate_row_nr__ < ?",
				dialect.getLimitHandler().processSql( query, toRowSelection( 1, 5 ) )
		);
	}
