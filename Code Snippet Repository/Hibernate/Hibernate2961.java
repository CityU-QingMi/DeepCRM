	@Override
	public final void close() {
		if ( this.closed ) {
			// noop if already closed
			return;
		}

		// not absolutely necessary, but does help with aggressive release
		//session.getJDBCContext().getConnectionManager().closeQueryStatement( ps, resultSet );
		session.getJdbcCoordinator().getResourceRegistry().release( ps );
		session.getJdbcCoordinator().afterStatementExecution();
		try {
			session.getPersistenceContext().getLoadContexts().cleanup( resultSet );
		}
		catch (Throwable ignore) {
			// ignore this error for now
			if ( LOG.isTraceEnabled() ) {
				LOG.tracev( "Exception trying to cleanup load context : {0}", ignore.getMessage() );
			}
		}

		this.closed = true;
	}
