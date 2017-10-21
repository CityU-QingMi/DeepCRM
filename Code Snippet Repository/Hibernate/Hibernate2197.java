	@Override
	public PreparedStatement prepareQueryStatement(
			String sql,
			final boolean isCallable,
			final ScrollMode scrollMode) {
		if ( scrollMode != null && !scrollMode.equals( ScrollMode.FORWARD_ONLY ) ) {
			if ( ! settings().isScrollableResultSetsEnabled() ) {
				throw new AssertionFailure("scrollable result sets are not enabled");
			}
			final PreparedStatement ps = new QueryStatementPreparationTemplate( sql ) {
				public PreparedStatement doPrepare() throws SQLException {
						return isCallable
								? connection().prepareCall( sql, scrollMode.toResultSetType(), ResultSet.CONCUR_READ_ONLY )
								: connection().prepareStatement( sql, scrollMode.toResultSetType(), ResultSet.CONCUR_READ_ONLY );
				}
			}.prepareStatement();
			jdbcCoordinator.registerLastQuery( ps );
			return ps;
		}
		else {
			final PreparedStatement ps = new QueryStatementPreparationTemplate( sql ) {
				public PreparedStatement doPrepare() throws SQLException {
						return isCallable
								? connection().prepareCall( sql )
								: connection().prepareStatement( sql );
				}
			}.prepareStatement();
			jdbcCoordinator.registerLastQuery( ps );
			return ps;
		}
	}
