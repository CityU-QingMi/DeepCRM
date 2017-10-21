	protected SqlStatementWrapper executeQueryStatement(
			String sqlStatement,
			QueryParameters queryParameters,
			boolean scroll,
			List<AfterLoadAction> afterLoadActions,
			SharedSessionContractImplementor session) throws SQLException {

		// Processing query filters.
		queryParameters.processFilters( sqlStatement, session );

		// Applying LIMIT clause.
		final LimitHandler limitHandler = getLimitHandler(
				queryParameters.getRowSelection()
		);
		String sql = limitHandler.processSql( queryParameters.getFilteredSQL(), queryParameters.getRowSelection() );

		// Adding locks and comments.
		sql = preprocessSQL( sql, queryParameters, getFactory(), afterLoadActions );

		final PreparedStatement st = prepareQueryStatement( sql, queryParameters, limitHandler, scroll, session );

		final ResultSet rs;

		if( queryParameters.isCallable() && isTypeOf( st, CallableStatement.class ) ) {
			final CallableStatement cs = st.unwrap( CallableStatement.class );

			rs = getResultSet(
					cs,
					queryParameters.getRowSelection(),
					limitHandler,
					queryParameters.hasAutoDiscoverScalarTypes(),
					session
			);
		}
		else {
			rs = getResultSet(
				st,
				queryParameters.getRowSelection(),
				limitHandler,
				queryParameters.hasAutoDiscoverScalarTypes(),
				session
			);
		}

		return new SqlStatementWrapper(
			st,
			rs
		);

	}
