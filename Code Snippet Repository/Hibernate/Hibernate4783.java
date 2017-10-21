	public void addColumn(ResultSet rs) throws SQLException {
		String column = rs.getString( "COLUMN_NAME" );

		if ( column == null ) {
			return;
		}

		if ( getColumnMetadata( column ) == null ) {
			ColumnMetadata info = new ColumnMetadata( rs );
			columns.put( info.getName().toLowerCase( Locale.ROOT ), info );
		}
	}
