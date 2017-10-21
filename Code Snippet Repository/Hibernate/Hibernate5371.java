	@Test
	@TestForIssue(jiraKey = "")
	public void testGetLimitStringDistinctWithinAggregationWithAliasNoAs() {
		final String distinctInAggregateSQL = "select aggregate_function(distinct p.n) f1 from table849752 p order by f1";

		assertEquals(
				"WITH query AS (SELECT inner_query.*, ROW_NUMBER() OVER (ORDER BY CURRENT_TIMESTAMP) as __hibernate_row_nr__ FROM ( " +
						"select TOP(?) aggregate_function(distinct p.n) f1 from table849752 p order by f1 ) inner_query ) " +
						"SELECT f1 FROM query WHERE __hibernate_row_nr__ >= ? AND __hibernate_row_nr__ < ?",
				dialect.getLimitHandler().processSql( distinctInAggregateSQL, toRowSelection( 2, 5 ) )
		);
	}
