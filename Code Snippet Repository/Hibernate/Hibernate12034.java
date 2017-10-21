	@Override
	public int count(Connection connection) {
		try ( Statement statement = connection.createStatement() ) {
			try ( ResultSet resultSet = statement.executeQuery(
					"select count(*) " +
							"from pg_stat_activity " +
							"where state ilike '%idle%'" ) ) {
				while ( resultSet.next() ) {
					return resultSet.getInt( 1 );
				}
				return 0;
			}
		}
		catch ( SQLException e ) {
			throw new IllegalStateException( e );
		}
	}
