	@Test
	@TestForIssue( jiraKey = "" )
	public void testInsertLimitWithOffsetClause() {
		final int limit = 50;
		final int offset = 200;
		final String input = "select * from tablename t where t.cat = 5";
		final String expected = "select * from tablename t where t.cat = 5 offset " + offset + " rows fetch next " + limit + " rows only";

		final String actual = new LocalDerbyDialect().getLimitString( input, offset, limit );
		assertEquals( expected, actual );
	}
