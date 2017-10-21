	@Test
	public void testThreadBoundCredentials() throws SQLException {
		DataSource dataSource = mock(DataSource.class);
		Connection connection = mock(Connection.class);
		given(dataSource.getConnection("user", "pw")).willReturn(connection);

		UserCredentialsDataSourceAdapter adapter = new UserCredentialsDataSourceAdapter();
		adapter.setTargetDataSource(dataSource);

		adapter.setCredentialsForCurrentThread("user", "pw");
		try {
			assertEquals(connection, adapter.getConnection());
		}
		finally {
			adapter.removeCredentialsFromCurrentThread();
		}
	}
