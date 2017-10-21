	protected final ResultSet getResultSet(
			final PreparedStatement st,
			final RowSelection selection,
			final LimitHandler limitHandler,
			final boolean autodiscovertypes,
			final SharedSessionContractImplementor session) throws SQLException, HibernateException {
		try {
			ResultSet rs = session.getJdbcCoordinator().getResultSetReturn().extract( st );

			return processResultSet(rs, selection, limitHandler, autodiscovertypes, session);
		}
		catch (SQLException | HibernateException e) {
			session.getJdbcCoordinator().getLogicalConnection().getResourceRegistry().release( st );
			session.getJdbcCoordinator().afterStatementExecution();
			throw e;
		}
	}
