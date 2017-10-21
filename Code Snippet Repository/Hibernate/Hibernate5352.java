	@Test
	@TestForIssue( jiraKey = "" )
	public void testInsertLimitClause() {
		final int limit = 50;
		final String input = "select * from tablename t where t.cat = 5";
		final String expected = "select * from tablename t where t.cat = 5 fetch first " + limit + " rows only";

		final String actual = new LocalDerbyDialect().getLimitString( input, 0, limit );
		assertEquals( expected, actual );
	}
