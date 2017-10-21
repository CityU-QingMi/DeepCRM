	public DataSourceUtils(
			String jdbcDriver,
			String jdbcUrl,
			String jdbcUser,
			String jdbcPass,
			SQLExpressionTemplate sqlExpressionTemplate) {
		this.jdbcDriver = jdbcDriver;
		this.jdbcUrl = jdbcUrl;
		this.jdbcUser = jdbcUser;
		this.jdbcPass = jdbcPass;
		this.sqlExpressionTemplate = sqlExpressionTemplate;
		createBasicDataSource();
	}
