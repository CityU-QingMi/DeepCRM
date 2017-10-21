	@Test
	public void testGetLimitString() {
		String input = "select distinct f1 as f53245 from table849752 order by f234, f67 desc";

		assertEquals(
				"with query as (select inner_query.*, row_number() over (order by current_timestamp) as __hibernate_row_nr__ from ( " +
						"select distinct top(?) f1 as f53245 from table849752 order by f234, f67 desc ) inner_query )" +
						" select f53245 from query where __hibernate_row_nr__ >= ? and __hibernate_row_nr__ < ?",
				dialect.getLimitHandler().processSql( input, toRowSelection( 10, 15 ) ).toLowerCase(Locale.ROOT) );
	}
