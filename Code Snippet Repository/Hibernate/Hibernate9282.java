	@Override
	protected DataSource dataSource() {
		DataSource dataSource = ReflectionUtil.newInstance( "com.mysql.cj.jdbc.MysqlDataSource" );
		if ( getDialect() instanceof MariaDBDialect ) {
			dataSource = ReflectionUtil.newInstance( "org.mariadb.jdbc.MariaDbDataSource" );
		}
		ReflectionUtil.setProperty( dataSource, "url", Environment.getProperties().getProperty( AvailableSettings.URL ) );
		ReflectionUtil.setProperty( dataSource, "user", Environment.getProperties().getProperty( AvailableSettings.USER ) );
		ReflectionUtil.setProperty( dataSource, "password", Environment.getProperties().getProperty( AvailableSettings.PASS ) );

		return dataSource;
	}
