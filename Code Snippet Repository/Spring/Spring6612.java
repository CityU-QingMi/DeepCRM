	@Test
	public void testSqlTypeValue() throws Exception {
		int[] testVal = new int[] { 1, 2 };
		given(callableStatement.execute()).willReturn(false);
		given(callableStatement.getUpdateCount()).willReturn(-1);
		given(callableStatement.getObject(2)).willReturn("OK");
		given(connection.prepareCall("{call " + SqlTypeValueStoredProcedure.SQL + "(?, ?)}")
				).willReturn(callableStatement);

		SqlTypeValueStoredProcedure stvsp = new SqlTypeValueStoredProcedure(dataSource);
		Map<String, Object> out = stvsp.executeTest(testVal);
		assertEquals("OK", out.get("out"));
		verify(callableStatement).setObject(1, testVal, Types.ARRAY);
		verify(callableStatement).registerOutParameter(2, Types.VARCHAR);
	}
