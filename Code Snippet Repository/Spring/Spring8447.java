	@AfterTransaction
	public void afterTransaction() {
		if ("test01".equals(testName.getMethodName())) {
			try {
				assertNumUsers(99);
				fail("Should throw a BadSqlGrammarException after test01, assuming 'drop-schema.sql' was executed");
			}
			catch (BadSqlGrammarException e) {
				/* expected */
			}
		}
	}
