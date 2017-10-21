	@Test
	public void parseSqlStatementWithEscapedColon() throws Exception {
		String expectedSql = "select '0\\:0' as a, foo from bar where baz < DATE(? 23:59:59) and baz = ?";
		String sql = "select '0\\:0' as a, foo from bar where baz < DATE(:p1 23\\:59\\:59) and baz = :p2";

		ParsedSql parsedSql = NamedParameterUtils.parseSqlStatement(sql);
		assertEquals(2, parsedSql.getParameterNames().size());
		assertEquals("p1", parsedSql.getParameterNames().get(0));
		assertEquals("p2", parsedSql.getParameterNames().get(1));
		String finalSql = NamedParameterUtils.substituteNamedParameters(parsedSql, null);
		assertEquals(expectedSql, finalSql);
	}
