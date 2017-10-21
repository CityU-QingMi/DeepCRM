	@Test
	public void testQueryForLongWithParamBeanWithCollection() throws Exception {
		given(resultSet.getMetaData()).willReturn(resultSetMetaData);
		given(resultSet.next()).willReturn(true, false);
		given(resultSet.getLong(1)).willReturn(87L);

		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(new ParameterCollectionBean(3, 5));
		long l = template.queryForObject("SELECT AGE FROM CUSTMR WHERE ID IN (:ids)", params, Long.class).longValue();

		assertEquals("Return of a long", 87, l);
		verify(connection).prepareStatement("SELECT AGE FROM CUSTMR WHERE ID IN (?, ?)");
		verify(preparedStatement).setObject(1, 3);
		verify(preparedStatement).setObject(2, 5);
	}
