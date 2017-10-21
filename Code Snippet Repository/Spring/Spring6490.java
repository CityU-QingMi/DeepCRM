	private void initializeAddInvoiceWithoutMetaData(boolean isFunction) throws SQLException {
		given(databaseMetaData.getDatabaseProductName()).willReturn("MyDB");
		given(databaseMetaData.getUserName()).willReturn("me");
		given(databaseMetaData.storesLowerCaseIdentifiers()).willReturn(true);
		given(callableStatement.execute()).willReturn(false);
		given(callableStatement.getUpdateCount()).willReturn(-1);
		if (isFunction) {
			given(callableStatement.getObject(1)).willReturn(4L);
			given(connection.prepareCall("{? = call add_invoice(?, ?)}")
					).willReturn(callableStatement);
		}
		else {
			given(callableStatement.getObject(3)).willReturn(4L);
			given(connection.prepareCall("{call add_invoice(?, ?, ?)}")
					).willReturn(callableStatement);
		}
	}
