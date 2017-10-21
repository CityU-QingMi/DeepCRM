	public static List<Map<String,?>> extractResults(ResultSet resultSet) throws SQLException {
		List<Map<String,?>> results = new ArrayList<Map<String, ?>>();

		while ( resultSet.next() ) {
			Map<String,Object> row = new HashMap<String, Object>();
			for ( int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++ ) {
				row.put(
						resultSet.getMetaData().getColumnLabel( i ),
						resultSet.getObject( i )
				);
				results.add( row );
			}
		}

		return results;
	}
