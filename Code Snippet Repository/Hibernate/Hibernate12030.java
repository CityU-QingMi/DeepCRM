	private Connection newConnection() {
		try {
			return DriverManager.getConnection(
					jdbcProperties.getUrl(),
					jdbcProperties.getUser(),
					jdbcProperties.getPassword()
			);
		}
		catch ( SQLException e ) {
			throw new IllegalStateException( e );
		}
	}
