	private void addIndex(ResultSet rs) throws SQLException {
		String index = rs.getString( "INDEX_NAME" );

		if ( index == null ) {
			return;
		}

		IndexMetadata info = getIndexMetadata( index );
		if ( info == null ) {
			info = new IndexMetadata( rs );
			indexes.put( info.getName().toLowerCase( Locale.ROOT ), info );
		}

		info.addColumn( getColumnMetadata( rs.getString( "COLUMN_NAME" ) ) );
	}
