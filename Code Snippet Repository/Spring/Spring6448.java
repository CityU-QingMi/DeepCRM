	@Test
	public void testExecute() throws SQLException {
		given(preparedStatement.executeUpdate()).willReturn(1);

		params.put("perfId", 1);
		params.put("priceId", 1);
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
		verify(preparedStatement).setObject(1, 1);
		verify(preparedStatement).setObject(2, 1);
		verify(preparedStatement).close();
		verify(connection).close();
	}
