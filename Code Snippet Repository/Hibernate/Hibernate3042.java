	public void close() throws JDBCException {
		if ( ps != null ) {
			LOG.debug( "Closing iterator" );
			session.getJdbcCoordinator().getResourceRegistry().release( ps );
			try {
				session.getPersistenceContext().getLoadContexts().cleanup( rs );
			}
			catch (Throwable ignore) {
				// ignore this error for now
				LOG.debugf( "Exception trying to cleanup load context : %s", ignore.getMessage() );
			}
			session.getJdbcCoordinator().afterStatementExecution();
			ps = null;
			rs = null;
			hasNext = false;
		}
	}
