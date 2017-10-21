	private ResultSet executeQuery(PreparedStatement ps, SessionEventListenerManager statsCollector)
			throws SQLException {
		try {
			statsCollector.jdbcExecuteStatementStart();
			return ps.executeQuery();
		}
		finally {
			statsCollector.jdbcExecuteStatementEnd();
		}
	}
