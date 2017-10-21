	private void releaseConnection() throws SQLException {
		if ( connection != null ) {
			try {
				serviceRegistry.getService( JdbcEnvironment.class ).getSqlExceptionHelper().logAndClearWarnings(
						connection );
			}
			finally {
				try {
					serviceRegistry.getService( ConnectionProvider.class ).closeConnection( connection );
				}
				finally {
					connection = null;
				}
			}
		}
	}
