	@Override
	public int count(Connection connection) {
		try ( Statement statement = connection.createStatement() ) {
			try ( ResultSet resultSet = statement.executeQuery(
					"SHOW PROCESSLIST" ) ) {
				int count = 0;
				while ( resultSet.next() ) {
					String state = resultSet.getString( "command" );
					if ( "sleep".equalsIgnoreCase( state ) ) {
						count++;
					}
				}
				return count;
			}
		}
		catch ( SQLException e ) {
			throw new IllegalStateException( e );
		}
	}
