	@Test
	public void malformedSqlStateCodes() {
		SQLException sex = new SQLException("Message", null, 1);
		testMalformedSqlStateCode(sex);

		sex = new SQLException("Message", "", 1);
		testMalformedSqlStateCode(sex);

		// One char's not allowed
		sex = new SQLException("Message", "I", 1);
		testMalformedSqlStateCode(sex);
	}
