	@Test
	public void testStoredProcedureExceptionTranslator() throws Exception {
		SQLException sqlException = new SQLException(
				"Syntax error or access violation exception", "42000");
		given(callableStatement.execute()).willThrow(sqlException);
		given(connection.prepareCall("{call " + StoredProcedureExceptionTranslator.SQL + "()}")
				).willReturn(callableStatement);
		StoredProcedureExceptionTranslator sproc = new StoredProcedureExceptionTranslator(dataSource);
		thrown.expect(CustomDataException.class);
		sproc.execute();
	}
