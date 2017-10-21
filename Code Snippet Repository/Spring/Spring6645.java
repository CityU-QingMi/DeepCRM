	private SQLErrorCodes getErrorCodesFromDataSource(String productName, SQLErrorCodesFactory factory) throws Exception {
		DatabaseMetaData databaseMetaData = mock(DatabaseMetaData.class);
		given(databaseMetaData.getDatabaseProductName()).willReturn(productName);

		Connection connection = mock(Connection.class);
		given(connection.getMetaData()).willReturn(databaseMetaData);

		DataSource dataSource = mock(DataSource.class);
		given(dataSource.getConnection()).willReturn(connection);

		SQLErrorCodesFactory secf = null;
		if (factory != null) {
			secf = factory;
		}
		else {
			secf = SQLErrorCodesFactory.getInstance();
		}

		SQLErrorCodes sec = secf.getErrorCodes(dataSource);


		SQLErrorCodes sec2 = secf.getErrorCodes(dataSource);
		assertSame("Cached per DataSource", sec2, sec);

		verify(connection).close();
		return sec;
	}
