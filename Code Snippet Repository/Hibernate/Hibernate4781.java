	private void addForeignKey(ResultSet rs) throws SQLException {
		String fk = rs.getString( "FK_NAME" );

		if ( fk == null ) {
			return;
		}

		ForeignKeyMetadata info = getForeignKeyMetadata( fk );
		if ( info == null ) {
			info = new ForeignKeyMetadata( rs );
			foreignKeys.put( info.getName().toLowerCase( Locale.ROOT ), info );
		}

		info.addReference( rs );
	}
