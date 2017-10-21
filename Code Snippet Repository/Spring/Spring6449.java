	@Test
	public void testExecuteWithTypedParameters() throws SQLException {
		given(preparedStatement.executeUpdate()).willReturn(1);

		params.put("perfId", new SqlParameterValue(Types.DECIMAL, 1));
		params.put("priceId", new SqlParameterValue(Types.INTEGER, 1));
		Object result = namedParameterTemplate.execute(UPDATE_NAMED_PARAMETERS, params,
				new PreparedStatementCallback<Object>() {
					@Override
					public Object doInPreparedStatement(PreparedStatement ps)
							throws SQLException {
						assertEquals(preparedStatement, ps);
						ps.executeUpdate();
						return "result";
					}
				});

		assertEquals("result", result);
		verify(connection).prepareStatement(UPDATE_NAMED_PARAMETERS_PARSED);
		verify(preparedStatement).setObject(1, 1, Types.DECIMAL);
		verify(preparedStatement).setObject(2, 1, Types.INTEGER);
		verify(preparedStatement).close();
		verify(connection).close();
	}
