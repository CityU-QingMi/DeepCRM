	protected final ResultSet getResultSet(
			final PreparedStatement st,
			final RowSelection selection,
			final LimitHandler limitHandler,
			final boolean autodiscovertypes,
			final SharedSessionContractImplementor session)
			throws SQLException, HibernateException {

		try {
			ResultSet rs = session.getJdbcCoordinator().getResultSetReturn().extract( st );
			rs = wrapResultSetIfEnabled( rs , session );

			if ( !limitHandler.supportsLimitOffset() || !LimitHelper.useLimit( limitHandler, selection ) ) {
				advance( rs, selection );
			}

			if ( autodiscovertypes ) {
				autoDiscoverTypes( rs );
			}
			return rs;
		}
		catch (SQLException | HibernateException ex) {
			session.getJdbcCoordinator().getResourceRegistry().release( st );
			session.getJdbcCoordinator().afterStatementExecution();
			throw ex;
		}
	}
