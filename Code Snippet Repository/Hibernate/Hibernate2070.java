	public Integer getIndexForColumnName(String columnName, ResultSet rs) throws SQLException {
		final Integer cached = columnNameToIndexCache.get( columnName );
		if ( cached != null ) {
			return cached;
		}
		else {
			final Integer index = Integer.valueOf( rs.findColumn( columnName ) );
			columnNameToIndexCache.put( columnName, index);
			return index;
		}
	}
