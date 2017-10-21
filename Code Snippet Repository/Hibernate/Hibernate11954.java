	protected NativeSQLStatement createNativeSQLStatement(final String sql, final Object[] params) {
		return new NativeSQLStatement() {
			public PreparedStatement prepare(Connection connection) throws SQLException {
				PreparedStatement pstmt = connection.prepareStatement( sql );
				int i = 1;
				for ( Object param : params ) {
					pstmt.setObject( i++, param );
				}
				return pstmt;
			}
		};
	}
