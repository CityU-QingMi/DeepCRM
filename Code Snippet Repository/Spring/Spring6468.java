	@Test
	public void testQueryForObjectWithMapAndInteger() throws Exception {
		given(resultSet.getMetaData()).willReturn(resultSetMetaData);
		given(resultSet.next()).willReturn(true, false);
		given(resultSet.getInt(1)).willReturn(22);

		Map<String, Object> params = new HashMap<>();
		params.put("id", 3);
		Object o = template.queryForObject("SELECT AGE FROM CUSTMR WHERE ID = :id",
				params, Integer.class);

		assertTrue("Correct result type", o instanceof Integer);
		verify(connection).prepareStatement("SELECT AGE FROM CUSTMR WHERE ID = ?");
		verify(preparedStatement).setObject(1, 3);
	}
