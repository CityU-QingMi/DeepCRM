	@Test
	public void testAddInvoicesWithinTransaction() throws Exception {
		given(callableStatement.execute()).willReturn(false);
		given(callableStatement.getUpdateCount()).willReturn(-1);
		given(callableStatement.getObject(3)).willReturn(4);
		given(connection.prepareCall("{call " + AddInvoice.SQL + "(?, ?, ?)}")
				).willReturn(callableStatement);
		TransactionSynchronizationManager.bindResource(dataSource, new ConnectionHolder(connection));
		try {
			testAddInvoice(1106, 3);
			verify(callableStatement).setObject(1, 1106, Types.INTEGER);
			verify(callableStatement).setObject(2, 3, Types.INTEGER);
			verify(callableStatement).registerOutParameter(3, Types.INTEGER);
			verify(connection, never()).close();
		}
		finally {
			TransactionSynchronizationManager.unbindResource(dataSource);
			connection.close();
		}
	}
