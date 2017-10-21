	@Override
	protected void cleanupTest() throws Exception {
		Connection connection = null;
		Statement stmnt = null;
		try {
			connection = services.getBootstrapJdbcConnectionAccess().obtainConnection();
			stmnt = connection.createStatement();
			stmnt.execute( "drop table SANDBOX_JDBC_TST if exists" );
		}
		finally {
			if ( stmnt != null ) {
				try {
					stmnt.close();
				}
				catch ( SQLException ignore ) {
				}
			}
			if ( connection != null ) {
				try {
					services.getBootstrapJdbcConnectionAccess().releaseConnection( connection );
				}
				catch ( SQLException ignore ) {
				}
			}
		}

		services.release();
	}
