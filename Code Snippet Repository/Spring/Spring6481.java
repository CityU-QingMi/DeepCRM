	private void initializeAddInvoiceWithMetaData(boolean isFunction) throws SQLException {
		ResultSet proceduresResultSet = mock(ResultSet.class);
		ResultSet procedureColumnsResultSet = mock(ResultSet.class);
		given(databaseMetaData.getDatabaseProductName()).willReturn("Oracle");
		given(databaseMetaData.getUserName()).willReturn("ME");
		given(databaseMetaData.storesUpperCaseIdentifiers()).willReturn(true);
		given(databaseMetaData.getProcedures("", "ME", "ADD_INVOICE")).willReturn(proceduresResultSet);
		given(databaseMetaData.getProcedureColumns("", "ME", "ADD_INVOICE", null)).willReturn(procedureColumnsResultSet);

		given(proceduresResultSet.next()).willReturn(true, false);
		given(proceduresResultSet.getString("PROCEDURE_NAME")).willReturn("add_invoice");

		given(procedureColumnsResultSet.next()).willReturn(true, true, true, false);
		given(procedureColumnsResultSet.getInt("DATA_TYPE")).willReturn(4);
		if (isFunction) {
			given(procedureColumnsResultSet.getString("COLUMN_NAME")).willReturn(null,"amount", "custid");
			given(procedureColumnsResultSet.getInt("COLUMN_TYPE")).willReturn(5, 1, 1);
			given(connection.prepareCall("{? = call ADD_INVOICE(?, ?)}")).willReturn(callableStatement);
			given(callableStatement.getObject(1)).willReturn(4L);
		}
		else {
			given(procedureColumnsResultSet.getString("COLUMN_NAME")).willReturn("amount", "custid", "newid");
			given(procedureColumnsResultSet.getInt("COLUMN_TYPE")).willReturn(1, 1, 4);
			given(connection.prepareCall("{call ADD_INVOICE(?, ?, ?)}")).willReturn(callableStatement);
			given(callableStatement.getObject(3)).willReturn(4L);
		}
		given(callableStatement.getUpdateCount()).willReturn(-1);
	}
