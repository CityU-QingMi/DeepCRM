	private ResultSet wrapResultSetIfEnabled(final ResultSet rs, final SharedSessionContractImplementor session) {
		if ( session.getFactory().getSessionFactoryOptions().isWrapResultSetsEnabled() ) {
			try {
				LOG.debugf( "Wrapping result set [%s]", rs );
				return session.getFactory()
						.getServiceRegistry()
						.getService( JdbcServices.class )
						.getResultSetWrapper().wrap( rs, retreiveColumnNameToIndexCache( rs ) );
			}
			catch (SQLException e) {
				LOG.unableToWrapResultSet( e );
				return rs;
			}
		}
		else {
			return rs;
		}
	}
