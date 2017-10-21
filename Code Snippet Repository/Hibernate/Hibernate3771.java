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
		sql = session.getJdbcServices().getJdbcEnvironment().getDialect()
				.addSqlHintOrComment(
					sql,
					queryParameters,
					session.getFactory().getSessionFactoryOptions().isCommentsEnabled()
				);

		final PreparedStatement st = prepareQueryStatement( sql, queryParameters, limitHandler, scroll, session );
		return new SqlStatementWrapper( st, getResultSet( st, queryParameters.getRowSelection(), limitHandler, queryParameters.hasAutoDiscoverScalarTypes(), session ) );
	}
