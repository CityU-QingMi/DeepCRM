	@Test
	public void testUpdate() throws SQLException {
		given(preparedStatement.executeUpdate()).willReturn(1);

		params.put("perfId", 1);
		params.put("priceId", 1);
		int rowsAffected = namedParameterTemplate.update(UPDATE_NAMED_PARAMETERS, params);

		assertEquals(1, rowsAffected);
		verify(connection).prepareStatement(UPDATE_NAMED_PARAMETERS_PARSED);
		verify(preparedStatement).setObject(1, 1);
		verify(preparedStatement).setObject(2, 1);
		verify(preparedStatement).close();
		verify(connection).close();
	}
