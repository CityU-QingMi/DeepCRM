	@Test
	public void testNoSuchStoredProcedure() throws Exception {
		SQLException sqlException = new SQLException(
				"Syntax error or access violation exception", "42000");
		given(callableStatement.execute()).willThrow(sqlException);
		given(connection.prepareCall("{call " + NoSuchStoredProcedure.SQL + "()}")).willReturn(
				callableStatement);

		NoSuchStoredProcedure sproc = new NoSuchStoredProcedure(dataSource);
		thrown.expect(BadSqlGrammarException.class);
		sproc.execute();
	}
