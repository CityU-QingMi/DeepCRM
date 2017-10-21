	@Test
	public void testExecuteNoParameters() throws SQLException {
		given(preparedStatement.executeUpdate()).willReturn(1);

		Object result = namedParameterTemplate.execute(SELECT_NO_PARAMETERS,
				new PreparedStatementCallback<Object>() {
					@Override
					public Object doInPreparedStatement(PreparedStatement ps)
							throws SQLException {
						assertEquals(preparedStatement, ps);
						ps.executeQuery();
						return "result";
					}
				});

		assertEquals("result", result);
		verify(connection).prepareStatement(SELECT_NO_PARAMETERS);
		verify(preparedStatement).close();
		verify(connection).close();
	}
