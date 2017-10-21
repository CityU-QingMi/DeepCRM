	@Override
	protected void prepareTest() throws Exception {
		services.prepare( true );

		Connection connection = null;
		Statement stmnt = null;
		try {
			connection = services.getBootstrapJdbcConnectionAccess().obtainConnection();
			stmnt = connection.createStatement();
			stmnt.execute( "drop table SANDBOX_JDBC_TST if exists" );
			stmnt.execute( "create table SANDBOX_JDBC_TST ( ID integer, NAME varchar(100) )" );
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
	}
