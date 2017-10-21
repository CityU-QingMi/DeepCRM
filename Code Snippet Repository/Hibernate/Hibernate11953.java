	protected NativeSQLStatement createNativeSQLStatementAllWKTParams(final String sql, final String wkt) {
		return new NativeSQLStatement() {
			public PreparedStatement prepare(Connection connection) throws SQLException {
				PreparedStatement pstmt = connection.prepareStatement( sql );
				for ( int i = 1; i <= numPlaceHoldersInSQL( sql ); i++ ) {
					pstmt.setString( i, wkt );
				}
				return pstmt;
			}
		};
	}
