	@Test
	@TestForIssue(jiraKey = "")
	public void testGetLimitStringSelectingAllColumns() {
		final String query = "select * from tab1 t1, tab2 t2 where t1.ref = t2.ref order by t1.id desc";

		assertEquals(
				"WITH query AS (SELECT inner_query.*, ROW_NUMBER() OVER (ORDER BY CURRENT_TIMESTAMP) as __hibernate_row_nr__ FROM ( " +
						"select TOP(?) * from tab1 t1, tab2 t2 where t1.ref = t2.ref order by t1.id desc ) inner_query ) " +
						"SELECT * FROM query WHERE __hibernate_row_nr__ >= ? AND __hibernate_row_nr__ < ?",
				dialect.getLimitHandler().processSql( query, toRowSelection( 1, 3 ) )
		);
	}
