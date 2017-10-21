	@Test
	public void testParameterMapper() throws Exception {
		given(callableStatement.execute()).willReturn(false);
		given(callableStatement.getUpdateCount()).willReturn(-1);
		given(callableStatement.getObject(2)).willReturn("OK");
		given(connection.prepareCall("{call " + ParameterMapperStoredProcedure.SQL + "(?, ?)}")
				).willReturn(callableStatement);

		ParameterMapperStoredProcedure pmsp = new ParameterMapperStoredProcedure(dataSource);
		Map<String, Object> out = pmsp.executeTest();
		assertEquals("OK", out.get("out"));

		verify(callableStatement).setString(eq(1), startsWith("Mock for Connection"));
		verify(callableStatement).registerOutParameter(2, Types.VARCHAR);
	}
