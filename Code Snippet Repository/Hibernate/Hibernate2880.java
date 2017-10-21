	public void cleanUpRows(String tableName, SharedSessionContractImplementor session) {
		final String sql = "delete from " + tableName + " where " + SESSION_ID_COLUMN_NAME + "=?";
		try {
			PreparedStatement ps = null;
			try {
				ps = session.getJdbcCoordinator().getStatementPreparer().prepareStatement( sql, false );
				bindSessionIdentifier( ps, session, 1 );
				session.getJdbcCoordinator().getResultSetReturn().executeUpdate( ps );
			}
			finally {
				if ( ps != null ) {
					try {
						session.getJdbcCoordinator().getLogicalConnection().getResourceRegistry().release( ps );
					}
					catch( Throwable ignore ) {
						// ignore
					}
				}
			}
		}
		catch (SQLException e) {
			throw session.getJdbcServices().getSqlExceptionHelper().convert( e, "Unable to clean up id table [" + tableName + "]", sql );
		}
	}
