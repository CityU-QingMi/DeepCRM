	@Test
	public void parseSqlStatementWithEmptyBracketsOrBracketsInQuotes() throws Exception {
		String expectedSql = "select foo from bar where baz = b:{}z";
		String sql = "select foo from bar where baz = b:{}z";
		ParsedSql parsedSql = NamedParameterUtils.parseSqlStatement(sql);
		assertEquals(0, parsedSql.getParameterNames().size());
		String finalSql = NamedParameterUtils.substituteNamedParameters(parsedSql, null);
		assertEquals(expectedSql, finalSql);

		String expectedSql2 = "select foo from bar where baz = 'b:{p1}z'";
		String sql2 = "select foo from bar where baz = 'b:{p1}z'";

		ParsedSql parsedSql2 = NamedParameterUtils.parseSqlStatement(sql2);
		assertEquals(0, parsedSql2.getParameterNames().size());
		String finalSql2 = NamedParameterUtils.substituteNamedParameters(parsedSql2, null);
		assertEquals(expectedSql2, finalSql2);
	}
