	private ColumnNameCache retreiveColumnNameToIndexCache(final ResultSet rs) throws SQLException {
		final ColumnNameCache cache = columnNameCache;
		if ( cache == null ) {
			//there is no need for a synchronized second check, as in worst case
			//we'll have allocated an unnecessary ColumnNameCache
			LOG.trace( "Building columnName -> columnIndex cache" );
			columnNameCache = new ColumnNameCache( rs.getMetaData().getColumnCount() );
			return columnNameCache;
		}
		else {
			return cache;
		}
	}
