	private void assertIsHana(SQLErrorCodes sec) {
		assertTrue(sec.getBadSqlGrammarCodes().length > 0);
		assertTrue(sec.getDataIntegrityViolationCodes().length > 0);

		assertTrue(Arrays.binarySearch(sec.getBadSqlGrammarCodes(), "368") >= 0);
		assertTrue(Arrays.binarySearch(sec.getPermissionDeniedCodes(), "10") >= 0);
		assertTrue(Arrays.binarySearch(sec.getDuplicateKeyCodes(), "301") >= 0);
		assertTrue(Arrays.binarySearch(sec.getDataIntegrityViolationCodes(), "461") >= 0);
		assertTrue(Arrays.binarySearch(sec.getDataAccessResourceFailureCodes(), "-813") >=0);
		assertTrue(Arrays.binarySearch(sec.getInvalidResultSetAccessCodes(), "582") >=0);
		assertTrue(Arrays.binarySearch(sec.getCannotAcquireLockCodes(), "131") >= 0);
		assertTrue(Arrays.binarySearch(sec.getCannotSerializeTransactionCodes(), "138") >= 0);
		assertTrue(Arrays.binarySearch(sec.getDeadlockLoserCodes(), "133") >= 0);

	}
