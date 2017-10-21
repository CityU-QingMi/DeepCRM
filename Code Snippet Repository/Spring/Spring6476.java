	@Test
	public void parseSqlStatementWithBracketDelimitedParameterNames() throws Exception {
		String expectedSql = "select foo from bar where baz = b??z";
		String sql = "select foo from bar where baz = b:{p1}:{p2}z";

		ParsedSql parsedSql = NamedParameterUtils.parseSqlStatement(sql);
		assertEquals(2, parsedSql.getParameterNames().size());
		assertEquals("p1", parsedSql.getParameterNames().get(0));
		assertEquals("p2", parsedSql.getParameterNames().get(1));
		String finalSql = NamedParameterUtils.substituteNamedParameters(parsedSql, null);
		assertEquals(expectedSql, finalSql);
	}
