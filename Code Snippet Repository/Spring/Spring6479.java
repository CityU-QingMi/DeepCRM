	@Test
	public void testNoSuchStoredProcedure() throws Exception {
		final String NO_SUCH_PROC = "x";
		SQLException sqlException = new SQLException("Syntax error or access violation exception", "42000");
		given(databaseMetaData.getDatabaseProductName()).willReturn("MyDB");
		given(databaseMetaData.getDatabaseProductName()).willReturn("MyDB");
		given(databaseMetaData.getUserName()).willReturn("me");
		given(databaseMetaData.storesLowerCaseIdentifiers()).willReturn(true);
		given(callableStatement.execute()).willThrow(sqlException);
		given(connection.prepareCall("{call " + NO_SUCH_PROC + "()}")).willReturn(callableStatement);
		SimpleJdbcCall sproc = new SimpleJdbcCall(dataSource).withProcedureName(NO_SUCH_PROC);
		thrown.expect(BadSqlGrammarException.class);
		thrown.expect(exceptionCause(sameInstance(sqlException)));
		try {
			sproc.execute();
		}
		finally {
			verify(callableStatement).close();
			verify(connection, atLeastOnce()).close();
		}
	}
