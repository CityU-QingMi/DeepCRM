	@Test
	@TestForIssue(jiraKey = "")
	public void testGetLimitStringCaseSensitive() {
		final String caseSensitiveSQL = "select persistent0_.id, persistent0_.uid AS tmp1, " +
				"(select case when persistent0_.name = 'Smith' then 'Neo' else persistent0_.id end) " +
				"from C_Customer persistent0_ " +
				"where persistent0_.type='Va' " +
				"order by persistent0_.Order";

		assertEquals(
				"WITH query AS (SELECT inner_query.*, ROW_NUMBER() OVER (ORDER BY CURRENT_TIMESTAMP) as __hibernate_row_nr__ FROM ( " +
						"select TOP(?) persistent0_.id as page0_, persistent0_.uid AS tmp1, " +
						"(select case when persistent0_.name = 'Smith' then 'Neo' else persistent0_.id end) as page1_ " +
						"from C_Customer persistent0_ where persistent0_.type='Va' order by persistent0_.Order ) " +
						"inner_query ) SELECT page0_, tmp1, page1_ FROM query WHERE __hibernate_row_nr__ >= ? AND __hibernate_row_nr__ < ?",
				dialect.getLimitHandler().processSql( caseSensitiveSQL, toRowSelection( 1, 2 ) )
		);
	}
