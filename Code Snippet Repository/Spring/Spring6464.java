	@Test
	public void testQueryForListWithParamMapAndSingleRowAndColumn() throws Exception {
		given(resultSet.getMetaData()).willReturn(resultSetMetaData);
		given(resultSet.next()).willReturn(true, false);
		given(resultSet.getObject(1)).willReturn(11);

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", 3);
		List<Map<String, Object>> li = template.queryForList(
				"SELECT AGE FROM CUSTMR WHERE ID < :id", params);

		assertEquals("All rows returned", 1, li.size());
		assertEquals("First row is Integer", 11,
				((Integer) li.get(0).get("age")).intValue());
		verify(connection).prepareStatement("SELECT AGE FROM CUSTMR WHERE ID < ?");
		verify(preparedStatement).setObject(1, 3);
	}
