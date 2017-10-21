	@Test
	@TestForIssue(jiraKey = "")
	public void testGetLimitStringWithNewlineAfterColumnList() {
		final String query = "select E.fieldA,E.fieldB\r\nFROM Employee E WHERE E.firstName = :firstName";
		assertEquals(
				"WITH query AS (SELECT inner_query.*, ROW_NUMBER() OVER (ORDER BY CURRENT_TIMESTAMP) as __hibernate_row_nr__ FROM ( " +
						"select E.fieldA as page0_,E.fieldB as page1_\r\n" +
						"FROM Employee E WHERE E.firstName = :firstName ) inner_query ) SELECT page0_, page1_ FROM query " +
						"WHERE __hibernate_row_nr__ >= ? AND __hibernate_row_nr__ < ?",
				dialect.getLimitHandler().processSql( query, toRowSelection( 1, 25 ) )
		);
	}
