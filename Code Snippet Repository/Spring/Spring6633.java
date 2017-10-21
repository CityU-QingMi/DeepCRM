	@Test
	public void batchExceptionTranslation() {
		SQLExceptionTranslator sext = new SQLErrorCodeSQLExceptionTranslator(ERROR_CODES);

		SQLException badSqlEx = new SQLException("", "", 1);
		BatchUpdateException batchUpdateEx = new BatchUpdateException();
		batchUpdateEx.setNextException(badSqlEx);
		BadSqlGrammarException bsgex = (BadSqlGrammarException) sext.translate("task", "SQL", batchUpdateEx);
		assertEquals("SQL", bsgex.getSql());
		assertEquals(badSqlEx, bsgex.getSQLException());
	}
