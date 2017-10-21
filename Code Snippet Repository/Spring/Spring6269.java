	@Override
	protected Connection doGetConnection(@Nullable String username, @Nullable String password) throws SQLException {
		// Create JDBCConnectionSpec using current isolation level value and read-only flag.
		Object connSpec = createConnectionSpec(
				getCurrentIsolationLevel(), getCurrentReadOnlyFlag(), username, password);
		if (logger.isDebugEnabled()) {
			logger.debug("Obtaining JDBC Connection from WebSphere DataSource [" +
					getTargetDataSource() + "], using ConnectionSpec [" + connSpec + "]");
		}
		// Create Connection through invoking WSDataSource.getConnection(JDBCConnectionSpec)
		Connection con = (Connection) ReflectionUtils.invokeJdbcMethod(
				this.wsDataSourceGetConnectionMethod, obtainTargetDataSource(), connSpec);
		Assert.state(con != null, "No Connection");
		return con;
	}
