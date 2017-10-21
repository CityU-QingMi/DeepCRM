	@Test
	public void parseSql() {
		String sql = "xxx :a yyyy :b :c :a zzzzz";
		ParsedSql psql = NamedParameterUtils.parseSqlStatement(sql);
		assertEquals("xxx ? yyyy ? ? ? zzzzz", NamedParameterUtils.substituteNamedParameters(psql, null));
		assertEquals("a", psql.getParameterNames().get(0));
		assertEquals("c", psql.getParameterNames().get(2));
		assertEquals("a", psql.getParameterNames().get(3));
		assertEquals(4, psql.getTotalParameterCount());
		assertEquals(3, psql.getNamedParameterCount());

		String sql2 = "xxx &a yyyy ? zzzzz";
		ParsedSql psql2 = NamedParameterUtils.parseSqlStatement(sql2);
		assertEquals("xxx ? yyyy ? zzzzz", NamedParameterUtils.substituteNamedParameters(psql2, null));
		assertEquals("a", psql2.getParameterNames().get(0));
		assertEquals(2, psql2.getTotalParameterCount());
		assertEquals(1, psql2.getNamedParameterCount());

		String sql3 = "xxx &a+:b" + '\t' + ":c%10 yyyy ? zzzzz";
		ParsedSql psql3 = NamedParameterUtils.parseSqlStatement(sql3);
		assertEquals("a", psql3.getParameterNames().get(0));
		assertEquals("b", psql3.getParameterNames().get(1));
		assertEquals("c", psql3.getParameterNames().get(2));
	}
