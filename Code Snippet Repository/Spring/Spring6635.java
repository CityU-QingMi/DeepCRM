	@Test
	public void customExceptionTranslation() {
		final String TASK = "TASK";
		final String SQL = "SQL SELECT *";
		final SQLErrorCodes customErrorCodes = new SQLErrorCodes();
		final CustomSQLErrorCodesTranslation customTranslation = new CustomSQLErrorCodesTranslation();

		customErrorCodes.setBadSqlGrammarCodes(new String[] {"1", "2"});
		customErrorCodes.setDataIntegrityViolationCodes(new String[] {"3", "4"});
		customTranslation.setErrorCodes(new String[] {"1"});
		customTranslation.setExceptionClass(CustomErrorCodeException.class);
		customErrorCodes.setCustomTranslations(new CustomSQLErrorCodesTranslation[] {customTranslation});

		SQLErrorCodeSQLExceptionTranslator sext = new SQLErrorCodeSQLExceptionTranslator();
		sext.setSqlErrorCodes(customErrorCodes);

		// Should custom translate this
		SQLException badSqlEx = new SQLException("", "", 1);
		assertEquals(CustomErrorCodeException.class, sext.translate(TASK, SQL, badSqlEx).getClass());
		assertEquals(badSqlEx, sext.translate(TASK, SQL, badSqlEx).getCause());

		// Shouldn't custom translate this
		SQLException invResEx = new SQLException("", "", 3);
		DataIntegrityViolationException diex = (DataIntegrityViolationException) sext.translate(TASK, SQL, invResEx);
		assertEquals(invResEx, diex.getCause());

		// Shouldn't custom translate this - invalid class
		exception.expect(IllegalArgumentException.class);
		customTranslation.setExceptionClass(String.class);
	}
