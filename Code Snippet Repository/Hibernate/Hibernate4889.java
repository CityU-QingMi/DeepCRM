	@Override
	public void releaseConnection(Connection connection) throws SQLException {
		// NOTE : reset auto-commit, but *do not* close the Connection.  The application handed us this connection

		if ( !wasInitiallyAutoCommit ) {
			try {
				if ( jdbcConnection.getAutoCommit() ) {
					jdbcConnection.setAutoCommit( false );
				}
			}
			catch (SQLException e) {
				log.info( "Was unable to reset JDBC connection to no longer be in auto-commit mode" );
			}
		}
	}
