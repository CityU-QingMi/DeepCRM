	private void verifyAddInvoiceWithoutMetaData(boolean isFunction) throws SQLException {
		if (isFunction) {
			verify(callableStatement).registerOutParameter(1, 4);
			verify(callableStatement).setObject(2, 1103, 4);
			verify(callableStatement).setObject(3, 3, 4);
		}
		else {
			verify(callableStatement).setObject(1, 1103, 4);
			verify(callableStatement).setObject(2, 3, 4);
			verify(callableStatement).registerOutParameter(3, 4);
		}
		verify(callableStatement).close();
	}
