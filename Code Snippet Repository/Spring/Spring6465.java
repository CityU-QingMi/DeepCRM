	@Test
	public void testQueryForListWithParamMapAndIntegerElementAndSingleRowAndColumn()
			throws Exception {
		given(resultSet.getMetaData()).willReturn(resultSetMetaData);
		given(resultSet.next()).willReturn(true, false);
		given(resultSet.getInt(1)).willReturn(11);

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", 3);
		List<Integer> li = template.queryForList("SELECT AGE FROM CUSTMR WHERE ID < :id",
				params, Integer.class);

		assertEquals("All rows returned", 1, li.size());
		assertEquals("First row is Integer", 11, li.get(0).intValue());
		verify(connection).prepareStatement("SELECT AGE FROM CUSTMR WHERE ID < ?");
		verify(preparedStatement).setObject(1, 3);
	}
