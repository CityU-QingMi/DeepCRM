	@Test
	public void testQueryForLongWithParamBean() throws Exception {
		given(resultSet.getMetaData()).willReturn(resultSetMetaData);
		given(resultSet.next()).willReturn(true, false);
		given(resultSet.getLong(1)).willReturn(87L);

		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(new ParameterBean(3));
		long l = template.queryForObject("SELECT AGE FROM CUSTMR WHERE ID = :id", params, Long.class).longValue();

		assertEquals("Return of a long", 87, l);
		verify(connection).prepareStatement("SELECT AGE FROM CUSTMR WHERE ID = ?");
		verify(preparedStatement).setObject(1, 3, Types.INTEGER);
	}
