	@Override
	public void release() {
		if ( jdbcConnection != null ) {
			try {
				jdbcContext.getJdbcConnectionAccess().releaseConnection( jdbcConnection );
			}
			catch (SQLException e) {
				throw jdbcContext.getSqlExceptionHelper().convert( e, "Unable to release JDBC Connection used for DDL execution" );
			}
		}

		if ( suspendedTransaction != null ) {
			try {
				jdbcContext.getServiceRegistry().getService( JtaPlatform.class ).retrieveTransactionManager().resume( suspendedTransaction );
			}
			catch (Exception e) {
				throw new HibernateException( "Unable to resume JTA transaction after DDL execution" );
			}
		}
	}
