	@Test
	public void testAddInvoicesUsingObjectArray() throws Exception {
		given(callableStatement.execute()).willReturn(false);
		given(callableStatement.getUpdateCount()).willReturn(-1);
		given(callableStatement.getObject(3)).willReturn(5);
		given(connection.prepareCall("{call " + AddInvoice.SQL + "(?, ?, ?)}")
				).willReturn(callableStatement);
		testAddInvoiceUsingObjectArray(1106, 4);
		verify(callableStatement).setObject(1, 1106, Types.INTEGER);
		verify(callableStatement).setObject(2, 4, Types.INTEGER);
		verify(callableStatement).registerOutParameter(3, Types.INTEGER);
	}
