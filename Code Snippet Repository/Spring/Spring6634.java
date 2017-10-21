	@SuppressWarnings("")
	@Test
	public void customTranslateMethodTranslation() {
		final String TASK = "TASK";
		final String SQL = "SQL SELECT *";
		final DataAccessException customDex = new DataAccessException("") {};

		final SQLException badSqlEx = new SQLException("", "", 1);
		SQLException intVioEx = new SQLException("", "", 6);

		SQLErrorCodeSQLExceptionTranslator sext = new SQLErrorCodeSQLExceptionTranslator() {
			@Override
			@Nullable
			protected DataAccessException customTranslate(String task, @Nullable String sql, SQLException sqlex) {
				assertEquals(TASK, task);
				assertEquals(SQL, sql);
				return (sqlex == badSqlEx) ? customDex : null;
			}
		};
		sext.setSqlErrorCodes(ERROR_CODES);

		// Shouldn't custom translate this
		assertEquals(customDex, sext.translate(TASK, SQL, badSqlEx));
		DataIntegrityViolationException diex = (DataIntegrityViolationException) sext.translate(TASK, SQL, intVioEx);
		assertEquals(intVioEx, diex.getCause());
	}
