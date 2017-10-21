	@Override
	public Connection getConnection() throws SQLException {
		Connection connection = super.getConnection();
		try(Statement statement = connection.createStatement()) {
			if ( dbName == null ) {
				try(ResultSet rs = statement.executeQuery( "SELECT DB_NAME()" )) {
					rs.next();
					dbName = rs.getString( 1 );
				}
			}
			statement.executeUpdate(String.format( RCS, dbName, "ON" ));
			statement.executeUpdate(String.format( SI, dbName, "ON" ));
		}
		catch (SQLException se) {
			fail( se.getMessage());
		}
		return connection;
	}
