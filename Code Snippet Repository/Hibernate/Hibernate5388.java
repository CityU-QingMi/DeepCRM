	@Test
	@TestForIssue(jiraKey = "")
	public void testGetLimitStringWithNewlineAfterSelectWithMultipleSpaces() {
		final String query = "select    " + System.lineSeparator() + "* FROM Employee E WHERE E.firstName = :firstName";
		assertEquals(
				"WITH query AS (SELECT inner_query.*, ROW_NUMBER() OVER (ORDER BY CURRENT_TIMESTAMP) as __hibernate_row_nr__ FROM ( " +
						query + " ) inner_query ) SELECT * FROM query WHERE __hibernate_row_nr__ >= ? AND __hibernate_row_nr__ < ?",
				dialect.getLimitHandler().processSql( query, toRowSelection( 1, 25 ) )
		);
	}
