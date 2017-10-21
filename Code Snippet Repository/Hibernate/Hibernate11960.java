	public void executeStatement(String sql) throws SQLException {
		Connection cn = null;
		try {
			cn = getDataSource().getConnection();
			cn.setAutoCommit( false );
			PreparedStatement statement = cn.prepareStatement( sql );
			LOG.info( "Executing statement: " + sql );
			statement.execute();
			cn.commit();
			statement.close();
		}
		finally {
			try {
				if ( cn != null ) {
					cn.close();
				}
			}
			catch (SQLException e) {
			} //do nothing.
		}
	}
