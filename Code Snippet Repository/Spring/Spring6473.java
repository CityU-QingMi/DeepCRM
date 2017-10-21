	@Test
	public void convertTypeMapToSqlParameterList() {
		MapSqlParameterSource namedParams = new MapSqlParameterSource();
		namedParams.addValue("a", "a", 1).addValue("b", "b", 2).addValue("c", "c", 3, "SQL_TYPE");
		assertSame(3, NamedParameterUtils
				.buildSqlParameterList(NamedParameterUtils.parseSqlStatement("xxx :a :b :c"), namedParams).size());
		assertSame(5, NamedParameterUtils
				.buildSqlParameterList(NamedParameterUtils.parseSqlStatement("xxx :a :b :c xx :a :b"), namedParams).size());
		assertSame(5, NamedParameterUtils
				.buildSqlParameterList(NamedParameterUtils.parseSqlStatement("xxx :a :a :a xx :a :a"), namedParams).size());
		assertEquals(2, NamedParameterUtils
				.buildSqlParameterList(NamedParameterUtils.parseSqlStatement("xxx :a :b :c xx :a :b"), namedParams).get(4).getSqlType());
		assertEquals("SQL_TYPE", NamedParameterUtils
				.buildSqlParameterList(NamedParameterUtils.parseSqlStatement("xxx :a :b :c"), namedParams).get(2).getTypeName());
	}
