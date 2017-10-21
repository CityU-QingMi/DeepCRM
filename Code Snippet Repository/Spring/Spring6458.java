	@Test
	public void testQueryForObjectWithParamMapAndListOfExpressionLists() throws Exception {
		given(resultSet.getMetaData()).willReturn(resultSetMetaData);
		given(resultSet.next()).willReturn(true, false);
		given(resultSet.getInt(1)).willReturn(22);

		MapSqlParameterSource params = new MapSqlParameterSource();
		List<Object[]> l1 = new ArrayList<>();
		l1.add(new Object[] {3, "Rod"});
		l1.add(new Object[] {4, "Juergen"});
		params.addValue("multiExpressionList", l1);
		Object o = template.queryForObject(
				"SELECT AGE FROM CUSTMR WHERE (ID, NAME) IN (:multiExpressionList)",
				params, Integer.class);

		assertTrue("Correct result type", o instanceof Integer);
		verify(connection).prepareStatement(
				"SELECT AGE FROM CUSTMR WHERE (ID, NAME) IN ((?, ?), (?, ?))");
		verify(preparedStatement).setObject(1, 3);
	}
