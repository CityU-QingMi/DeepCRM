	public void prepare(boolean allowAggressiveRelease) throws SQLException {
		dialect = ConnectionProviderBuilder.getCorrespondingDialect();
		connectionProvider = ConnectionProviderBuilder.buildConnectionProvider( allowAggressiveRelease );
		sqlStatementLogger = new SqlStatementLogger( true, false );

		Connection jdbcConnection = connectionProvider.getConnection();
		try {
			jdbcEnvironment = new JdbcEnvironmentImpl( jdbcConnection.getMetaData(), dialect );
		}
		finally {
			try {
				connectionProvider.closeConnection( jdbcConnection );
			}
			catch (SQLException ignore) {
			}
		}

		this.jdbcConnectionAccess = new JdbcConnectionAccessImpl( connectionProvider );
	}
