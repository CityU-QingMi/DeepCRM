	@Test
	@TestForIssue(jiraKey = "")
	public void testGetLimitStringWithCastOperatorWithoutAliases() {
		final String query = "select cast(lc302_doku6_.redniBrojStavke as varchar(255)), lc302_doku6_.dokumentiID " +
				"from LC302_Dokumenti lc302_doku6_ order by lc302_doku6_.dokumentiID DESC";

		assertEquals(
				"WITH query AS (SELECT inner_query.*, ROW_NUMBER() OVER (ORDER BY CURRENT_TIMESTAMP) as __hibernate_row_nr__ FROM ( " +
						"select TOP(?) cast(lc302_doku6_.redniBrojStavke as varchar(255)) as page0_, lc302_doku6_.dokumentiID as page1_ " +
						"from LC302_Dokumenti lc302_doku6_ order by lc302_doku6_.dokumentiID DESC ) inner_query ) " +
						"SELECT page0_, page1_ FROM query WHERE __hibernate_row_nr__ >= ? AND __hibernate_row_nr__ < ?",
				dialect.getLimitHandler().processSql( query, toRowSelection( 1, 3 ) )
		);
	}
