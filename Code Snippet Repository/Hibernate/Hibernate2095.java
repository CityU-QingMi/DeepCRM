	protected JDBCException convertSqlException(String message, SQLException e) {
		// if JdbcServices#getSqlExceptionHelper is available, use it...
		final JdbcServices jdbcServices = serviceRegistry.getService( JdbcServices.class );
		if ( jdbcServices != null && jdbcServices.getSqlExceptionHelper() != null ) {
			return jdbcServices.getSqlExceptionHelper().convert( e, message, null );
		}

		// likely we are still in the process of initializing the ServiceRegistry, so use the simplified
		// SQLException conversion
		return simpleConverterAccess.getValue().convert( e, message, null );
	}
