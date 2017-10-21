	@Test
	public void testQueryForListWithParamMapAndEmptyResult() throws Exception {
		given(resultSet.next()).willReturn(false);

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", 3);
		List<Map<String, Object>> li = template.queryForList(
				"SELECT AGE FROM CUSTMR WHERE ID < :id", params);

		assertEquals("All rows returned", 0, li.size());
		verify(connection).prepareStatement("SELECT AGE FROM CUSTMR WHERE ID < ?");
		verify(preparedStatement).setObject(1, 3);
	}
