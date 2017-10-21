	private void initColumns(DatabaseMetaData meta) throws SQLException {
		ResultSet rs = null;

		try {
			rs = meta.getColumns( catalog, schema, name, "%" );
			while ( rs.next() ) {
				addColumn( rs );
			}
		}
		finally {
			if ( rs != null ) {
				rs.close();
			}
		}
	}
