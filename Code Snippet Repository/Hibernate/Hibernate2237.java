	public int performExecuteUpdate(
			QueryParameters queryParameters,
			SharedSessionContractImplementor session) throws HibernateException {

		coordinateSharedCacheCleanup( session );

		if ( queryParameters.isCallable() ) {
			throw new IllegalArgumentException("callable not yet supported for native queries");
		}

		int result = 0;
		PreparedStatement ps;
		try {
			queryParameters.processFilters( this.customQuery.getSQL(), session );
			final String sql = session.getJdbcServices().getDialect()
					.addSqlHintOrComment(
						queryParameters.getFilteredSQL(),
						queryParameters,
						session.getFactory().getSessionFactoryOptions().isCommentsEnabled()
					);

			ps = session.getJdbcCoordinator().getStatementPreparer().prepareStatement( sql, false );

			try {
				int col = 1;
				col += bindPositionalParameters( ps, queryParameters, col, session );
				col += bindNamedParameters( ps, queryParameters.getNamedParameters(), col, session );
				result = session.getJdbcCoordinator().getResultSetReturn().executeUpdate( ps );
			}
			finally {
				if ( ps != null ) {
					session.getJdbcCoordinator().getLogicalConnection().getResourceRegistry().release( ps );
					session.getJdbcCoordinator().afterStatementExecution();
				}
			}
		}
		catch (SQLException sqle) {
			throw session.getFactory().getSQLExceptionHelper().convert(
					sqle,
					"could not execute native bulk manipulation query",
					this.sourceQuery
			);
		}

		return result;
	}
