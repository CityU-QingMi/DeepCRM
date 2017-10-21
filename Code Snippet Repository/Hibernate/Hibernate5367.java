	@Test
	@TestForIssue(jiraKey = "")
	public void testGetLimitStringWithSelectDistinctSubselectNotFirst() {
		final String selectDistinctSubselectSQL = "select page0_.CONTENTID as CONTENT1_12_ FROM CONTEXT page0_ " +
				"where page0_.CONTENTTYPE='PAGE' and (page0_.CONTENTID in " +
				"(select distinct page2_.PREVVER from CONTENT page2_ where (page2_.PREVVER is not null)))";

		assertEquals(
				"WITH query AS (SELECT inner_query.*, ROW_NUMBER() OVER (ORDER BY CURRENT_TIMESTAMP) as __hibernate_row_nr__ " +
				"FROM ( " + selectDistinctSubselectSQL + " ) inner_query ) " +
				"SELECT CONTENT1_12_ FROM query WHERE __hibernate_row_nr__ >= ? AND __hibernate_row_nr__ < ?",
				dialect.getLimitHandler().processSql( selectDistinctSubselectSQL, toRowSelection( 1, 5 ) )
		);
	}
