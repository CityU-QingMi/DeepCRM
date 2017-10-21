	@Test
	public void errorCodeTranslation() {
		SQLExceptionTranslator sext = new SQLErrorCodeSQLExceptionTranslator(ERROR_CODES);

		SQLException badSqlEx = new SQLException("", "", 1);
		BadSqlGrammarException bsgex = (BadSqlGrammarException) sext.translate("task", "SQL", badSqlEx);
		assertEquals("SQL", bsgex.getSql());
		assertEquals(badSqlEx, bsgex.getSQLException());

		SQLException invResEx = new SQLException("", "", 4);
		InvalidResultSetAccessException irsex = (InvalidResultSetAccessException) sext.translate("task", "SQL", invResEx);
		assertEquals("SQL", irsex.getSql());
		assertEquals(invResEx, irsex.getSQLException());

		checkTranslation(sext, 5, DataAccessResourceFailureException.class);
		checkTranslation(sext, 6, DataIntegrityViolationException.class);
		checkTranslation(sext, 7, CannotAcquireLockException.class);
		checkTranslation(sext, 8, DeadlockLoserDataAccessException.class);
		checkTranslation(sext, 9, CannotSerializeTransactionException.class);
		checkTranslation(sext, 10, DuplicateKeyException.class);

		SQLException dupKeyEx = new SQLException("", "", 10);
		DataAccessException dksex = sext.translate("task", "SQL", dupKeyEx);
		assertTrue("Not instance of DataIntegrityViolationException",
				DataIntegrityViolationException.class.isAssignableFrom(dksex.getClass()));

		// Test fallback. We assume that no database will ever return this error code,
		// but 07xxx will be bad grammar picked up by the fallback SQLState translator
		SQLException sex = new SQLException("", "07xxx", 666666666);
		BadSqlGrammarException bsgex2 = (BadSqlGrammarException) sext.translate("task", "SQL2", sex);
		assertEquals("SQL2", bsgex2.getSql());
		assertEquals(sex, bsgex2.getSQLException());
	}
