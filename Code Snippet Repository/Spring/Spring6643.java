	@Test
	public void testDataSourceWithNullMetadata() throws Exception {
		Connection connection = mock(Connection.class);
		DataSource dataSource = mock(DataSource.class);
		given(dataSource.getConnection()).willReturn(connection);

		SQLErrorCodes sec = SQLErrorCodesFactory.getInstance().getErrorCodes(dataSource);
		assertIsEmpty(sec);

		verify(connection).close();
	}
