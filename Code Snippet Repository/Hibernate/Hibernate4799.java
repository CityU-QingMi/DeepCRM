	@Override
	public Connection getJdbcConnection() {
		if ( jdbcConnection == null ) {
			try {
				jdbcConnection = jdbcConnectionAccess.obtainConnection();
			}
			catch (SQLException e) {
				throw jdbcEnvironment.getSqlExceptionHelper().convert( e, "Unable to obtain JDBC Connection" );
			}
		}
		return jdbcConnection;
	}
