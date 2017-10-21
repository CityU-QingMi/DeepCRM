	@Override
	public Connection obtainConnection() throws SQLException {
		try {
			listener.jdbcConnectionAcquisitionStart();
			return connectionProvider.getConnection();
		}
		finally {
			listener.jdbcConnectionAcquisitionEnd();
		}
	}
