	@Test
	public void testNumericWithScale() throws Exception {
		given(callableStatement.execute()).willReturn(false);
		given(callableStatement.getUpdateCount()).willReturn(-1);
		given(callableStatement.getObject(1)).willReturn(new BigDecimal("12345.6789"));
		given(connection.prepareCall("{call " + NumericWithScaleStoredProcedure.SQL + "(?)}")
				).willReturn(callableStatement);
		NumericWithScaleStoredProcedure nwssp = new NumericWithScaleStoredProcedure(dataSource);
		Map<String, Object> out = nwssp.executeTest();
		assertEquals(new BigDecimal("12345.6789"), out.get("out"));
		verify(callableStatement).registerOutParameter(1, Types.DECIMAL, 4);
	}
