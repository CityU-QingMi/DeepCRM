	@Test
	public void testGetFromDataSourceWithSQLException() throws Exception {
		SQLException expectedSQLException = new SQLException();

		DataSource dataSource = mock(DataSource.class);
		given(dataSource.getConnection()).willThrow(expectedSQLException);

		SQLErrorCodes sec = SQLErrorCodesFactory.getInstance().getErrorCodes(dataSource);
		assertIsEmpty(sec);
	}
