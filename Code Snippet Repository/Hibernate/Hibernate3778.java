	private ResultSet wrapResultSetIfEnabled(final ResultSet rs, final SharedSessionContractImplementor session) {
		if ( session.getFactory().getSessionFactoryOptions().isWrapResultSetsEnabled() ) {
			try {
				if ( log.isDebugEnabled() ) {
					log.debugf( "Wrapping result set [%s]", rs );
				}
				ResultSetWrapper wrapper = session.getFactory()
						.getServiceRegistry()
						.getService( JdbcServices.class )
						.getResultSetWrapper();
				// synchronized to avoid multi-thread access issues
				// Apparently the comment about this needing synchronization was introduced when AbstractLoadPlanBasedLoader first appeared
				// in version control. Would need to investigate if it's still needed?
				synchronized ( this ) {
					return wrapper.wrap( rs, retreiveColumnNameToIndexCache( rs ) );
				}
			}
			catch(SQLException e) {
				log.unableToWrapResultSet( e );
				return rs;
			}
		}
		else {
			return rs;
		}
	}
