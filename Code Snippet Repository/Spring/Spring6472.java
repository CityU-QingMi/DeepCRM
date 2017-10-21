	@Test
	public void convertTypeMapToArray() {
		MapSqlParameterSource namedParams = new MapSqlParameterSource();
		namedParams.addValue("a", "a", 1).addValue("b", "b", 2).addValue("c", "c", 3);
		assertSame(3, NamedParameterUtils
				.buildSqlTypeArray(NamedParameterUtils.parseSqlStatement("xxx :a :b :c"), namedParams).length);
		assertSame(5, NamedParameterUtils
				.buildSqlTypeArray(NamedParameterUtils.parseSqlStatement("xxx :a :b :c xx :a :b"), namedParams).length);
		assertSame(5, NamedParameterUtils
				.buildSqlTypeArray(NamedParameterUtils.parseSqlStatement("xxx :a :a :a xx :a :a"), namedParams).length);
		assertEquals(2, NamedParameterUtils
				.buildSqlTypeArray(NamedParameterUtils.parseSqlStatement("xxx :a :b :c xx :a :b"), namedParams)[4]);
	}
