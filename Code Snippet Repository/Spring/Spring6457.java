	@Test
	public void testQueryForObjectWithParamMapAndList() throws Exception {
		String sql = "SELECT AGE FROM CUSTMR WHERE ID IN (:ids)";
		String sqlToUse = "SELECT AGE FROM CUSTMR WHERE ID IN (?, ?)";
		given(resultSet.getMetaData()).willReturn(resultSetMetaData);
		given(resultSet.next()).willReturn(true, false);
		given(resultSet.getInt(1)).willReturn(22);

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("ids", Arrays.asList(3, 4));
		Object o = template.queryForObject(sql, params, Integer.class);

		assertTrue("Correct result type", o instanceof Integer);
		verify(connection).prepareStatement(sqlToUse);
		verify(preparedStatement).setObject(1, 3);
	}
