	protected Object createConnectionSpec(@Nullable Integer isolationLevel, @Nullable Boolean readOnlyFlag,
			@Nullable String username, @Nullable String password) throws SQLException {

		Object connSpec = ReflectionUtils.invokeJdbcMethod(this.newJdbcConnSpecMethod, null);
		Assert.state(connSpec != null, "No JDBCConnectionSpec");
		if (isolationLevel != null) {
			ReflectionUtils.invokeJdbcMethod(this.setTransactionIsolationMethod, connSpec, isolationLevel);
		}
		if (readOnlyFlag != null) {
			ReflectionUtils.invokeJdbcMethod(this.setReadOnlyMethod, connSpec, readOnlyFlag);
		}
		// If the username is empty, we'll simply let the target DataSource
		// use its default credentials.
		if (StringUtils.hasLength(username)) {
			ReflectionUtils.invokeJdbcMethod(this.setUserNameMethod, connSpec, username);
			ReflectionUtils.invokeJdbcMethod(this.setPasswordMethod, connSpec, password);
		}
		return connSpec;
	}
