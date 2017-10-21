	@Test
	public void parseSqlContainingComments() {
		String sql1 = "/*+ HINT */ xxx /* comment ? */ :a yyyy :b :c :a zzzzz -- :xx XX\n";
		ParsedSql psql1 = NamedParameterUtils.parseSqlStatement(sql1);
		assertEquals("/*+ HINT */ xxx /* comment ? */ ? yyyy ? ? ? zzzzz -- :xx XX\n",
				NamedParameterUtils.substituteNamedParameters(psql1, null));
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("a", "a");
		paramMap.addValue("b", "b");
		paramMap.addValue("c", "c");
		Object[] params = NamedParameterUtils.buildValueArray(psql1, paramMap, null);
		assertEquals(4, params.length);
		assertEquals("a", params[0]);
		assertEquals("b", params[1]);
		assertEquals("c", params[2]);
		assertEquals("a", params[3]);

		String sql2 = "/*+ HINT */ xxx /* comment ? */ :a yyyy :b :c :a zzzzz -- :xx XX";
		ParsedSql psql2 = NamedParameterUtils.parseSqlStatement(sql2);
		assertEquals("/*+ HINT */ xxx /* comment ? */ ? yyyy ? ? ? zzzzz -- :xx XX",
				NamedParameterUtils.substituteNamedParameters(psql2, null));

		String sql3 = "/*+ HINT */ xxx /* comment ? */ :a yyyy :b :c :a zzzzz /* :xx XX*";
		ParsedSql psql3 = NamedParameterUtils.parseSqlStatement(sql3);
		assertEquals("/*+ HINT */ xxx /* comment ? */ ? yyyy ? ? ? zzzzz /* :xx XX*",
				NamedParameterUtils.substituteNamedParameters(psql3, null));

		String sql4 = "/*+ HINT */ xxx /* comment :a ? */ :a yyyy :b :c :a zzzzz /* :xx XX*";
		ParsedSql psql4 = NamedParameterUtils.parseSqlStatement(sql4);
		Map<String, String> parameters = Collections.singletonMap("a", "0");
		assertEquals("/*+ HINT */ xxx /* comment :a ? */ ? yyyy ? ? ? zzzzz /* :xx XX*",
				NamedParameterUtils.substituteNamedParameters(psql4, new MapSqlParameterSource(parameters)));
	}
