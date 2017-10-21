	private void initIndexes(DatabaseMetaData meta) throws SQLException {
		ResultSet rs = null;

		try {
			rs = meta.getIndexInfo( catalog, schema, name, false, true );

			while ( rs.next() ) {
				if ( rs.getShort( "TYPE" ) == DatabaseMetaData.tableIndexStatistic ) {
					continue;
				}
				addIndex( rs );
			}
		}
		finally {
			if ( rs != null ) {
				rs.close();
			}
		}
	}
