	private ResultSet processResultSet(
			ResultSet rs,
			final RowSelection selection,
			final LimitHandler limitHandler,
			final boolean autodiscovertypes,
			final SharedSessionContractImplementor session
	) throws SQLException, HibernateException {
		rs = wrapResultSetIfEnabled( rs, session );

		if ( !limitHandler.supportsLimitOffset() || !LimitHelper.useLimit( limitHandler, selection ) ) {
			advance( rs, selection );
		}

		if ( autodiscovertypes ) {
			autoDiscoverTypes( rs );
		}
		return rs;
	}
