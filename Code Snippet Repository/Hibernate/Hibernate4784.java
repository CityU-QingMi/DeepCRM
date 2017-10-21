	private void initForeignKeys(DatabaseMetaData meta) throws SQLException {
		ResultSet rs = null;

		try {
			rs = meta.getImportedKeys( catalog, schema, name );
			while ( rs.next() ) {
				addForeignKey( rs );
			}
		}
		finally {
			if ( rs != null ) {
				rs.close();
			}
		}
	}
