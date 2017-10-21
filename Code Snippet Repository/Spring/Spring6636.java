	private void assertIsSQLServer(SQLErrorCodes sec) {
		assertThat(sec.getDatabaseProductName(), equalTo("Microsoft SQL Server"));

		assertTrue(sec.getBadSqlGrammarCodes().length > 0);

		assertTrue(Arrays.binarySearch(sec.getBadSqlGrammarCodes(), "156") >= 0);
		assertTrue(Arrays.binarySearch(sec.getBadSqlGrammarCodes(), "170") >= 0);
		assertTrue(Arrays.binarySearch(sec.getBadSqlGrammarCodes(), "207") >= 0);
		assertTrue(Arrays.binarySearch(sec.getBadSqlGrammarCodes(), "208") >= 0);
		assertTrue(Arrays.binarySearch(sec.getBadSqlGrammarCodes(), "209") >= 0);
		assertFalse(Arrays.binarySearch(sec.getBadSqlGrammarCodes(), "9xx42") >= 0);

		assertTrue(sec.getPermissionDeniedCodes().length > 0);
		assertTrue(Arrays.binarySearch(sec.getPermissionDeniedCodes(), "229") >= 0);

		assertTrue(sec.getDuplicateKeyCodes().length > 0);
		assertTrue(Arrays.binarySearch(sec.getDuplicateKeyCodes(), "2601") >= 0);
		assertTrue(Arrays.binarySearch(sec.getDuplicateKeyCodes(), "2627") >= 0);

		assertTrue(sec.getDataIntegrityViolationCodes().length > 0);
		assertTrue(Arrays.binarySearch(sec.getDataIntegrityViolationCodes(), "544") >= 0);
		assertTrue(Arrays.binarySearch(sec.getDataIntegrityViolationCodes(), "8114") >= 0);
		assertTrue(Arrays.binarySearch(sec.getDataIntegrityViolationCodes(), "8115") >= 0);

		assertTrue(sec.getDataAccessResourceFailureCodes().length > 0);
		assertTrue(Arrays.binarySearch(sec.getDataAccessResourceFailureCodes(), "4060") >= 0);

		assertTrue(sec.getCannotAcquireLockCodes().length > 0);
		assertTrue(Arrays.binarySearch(sec.getCannotAcquireLockCodes(), "1222") >= 0);

		assertTrue(sec.getDeadlockLoserCodes().length > 0);
		assertTrue(Arrays.binarySearch(sec.getDeadlockLoserCodes(), "1205") >= 0);
	}
