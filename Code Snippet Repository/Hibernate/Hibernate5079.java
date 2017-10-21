	private Timestamp usePreparedStatement(String timestampSelectString, SharedSessionContractImplementor session) {
		PreparedStatement ps = null;
		try {
			ps = session
					.getJdbcCoordinator()
					.getStatementPreparer()
					.prepareStatement( timestampSelectString, false );
			ResultSet rs = session.getJdbcCoordinator().getResultSetReturn().extract( ps );
			rs.next();
			Timestamp ts = rs.getTimestamp( 1 );
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
					"could not select current db timestamp",
					timestampSelectString
			);
		}
		finally {
			if ( ps != null ) {
				session.getJdbcCoordinator().getLogicalConnection().getResourceRegistry().release( ps );
				session.getJdbcCoordinator().afterStatementExecution();
			}
		}
	}
