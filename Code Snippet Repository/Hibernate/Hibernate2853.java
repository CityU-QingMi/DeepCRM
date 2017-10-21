	private void cleanUpRows(String tableName, SharedSessionContractImplementor session) {
		final String sql = "delete from " + tableName;
		PreparedStatement ps = null;
		try {
			ps = session.getJdbcCoordinator().getStatementPreparer().prepareStatement( sql, false );
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
