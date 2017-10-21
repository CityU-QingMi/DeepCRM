	@Override
	public void releaseConnection(Connection connection) throws SQLException {
		try {
			listener.jdbcConnectionReleaseStart();
			connectionProvider.closeConnection( connection );
		}
		finally {
			listener.jdbcConnectionReleaseEnd();
		}
	}
