	private Timestamp useCallableStatement(String callString, SharedSessionContractImplementor session) {
		CallableStatement cs = null;
		try {
			cs = (CallableStatement) session
					.getJdbcCoordinator()
					.getStatementPreparer()
					.prepareStatement( callString, true );
			cs.registerOutParameter( 1, java.sql.Types.TIMESTAMP );
			session.getJdbcCoordinator().getResultSetReturn().execute( cs );
			Timestamp ts = cs.getTimestamp( 1 );
			if ( LOG.isTraceEnabled() ) {
				LOG.tracev(
						"Current timestamp retrieved from db : {0} (nanos={1}, time={2})",
						ts,
						ts.getNanos(),
						ts.getTime()
				);
			}
			return ts;
		}
		catch (SQLException e) {
			throw session.getJdbcServices().getSqlExceptionHelper().convert(
					e,
					"could not call current db timestamp function",
					callString
			);
		}
		finally {
			if ( cs != null ) {
				session.getJdbcCoordinator().getLogicalConnection().getResourceRegistry().release( cs );
				session.getJdbcCoordinator().afterStatementExecution();
			}
		}
	}
