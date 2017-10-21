	@Test
	@TestForIssue(jiraKey = "")
	public void testGetLimitStringWithSelectClauseNestedQueryUsingParenthesisOnlyTop() {
		final String query = "select t1.c1 as col_0_0, (select case when count(t2.c1)>0 then 'ADDED' else 'UNMODIFIED' end from table2 t2 WHERE (t2.c1 in (?))) as col_1_0 from table1 t1 WHERE 1=1 ORDER BY t1.c1 ASC";

		assertEquals(
				"select TOP(?) t1.c1 as col_0_0, (select case when count(t2.c1)>0 then 'ADDED' else 'UNMODIFIED' end from table2 t2 WHERE (t2.c1 in (?))) as col_1_0 from table1 t1 WHERE 1=1 ORDER BY t1.c1 ASC",
				dialect.getLimitHandler().processSql( query, toRowSelection( 0, 5 ) )
		);
	}
