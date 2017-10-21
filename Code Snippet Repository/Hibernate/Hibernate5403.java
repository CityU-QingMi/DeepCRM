	@Test
	@TestForIssue(jiraKey = "")
	public void testGetLimitStringWithSelectDistinctSubselect() {
		final String selectDistinctSubselectSQL = "select page0_.CONTENTID as CONTENT1_12_ " +
				"where page0_.CONTENTTYPE='PAGE' and (page0_.CONTENTID in " +
				"(select distinct page2_.PREVVER from CONTENT page2_ where (page2_.PREVVER is not null)))";

		assertEquals(
				"select TOP(?) page0_.CONTENTID as CONTENT1_12_ " +
						"where page0_.CONTENTTYPE='PAGE' and (page0_.CONTENTID in " +
						"(select distinct page2_.PREVVER from CONTENT page2_ where (page2_.PREVVER is not null)))",
				dialect.getLimitHandler().processSql( selectDistinctSubselectSQL, toRowSelection( 0, 5 ) )
		);
	}
