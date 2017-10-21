	private List doQuery(
			final SharedSessionContractImplementor session,
			final QueryParameters queryParameters,
			final boolean returnProxies,
			final ResultTransformer forcedResultTransformer) throws SQLException, HibernateException {

		final RowSelection selection = queryParameters.getRowSelection();
		final int maxRows = LimitHelper.hasMaxRows( selection ) ?
				selection.getMaxRows() :
				Integer.MAX_VALUE;

		final List<AfterLoadAction> afterLoadActions = new ArrayList<AfterLoadAction>();

		final SqlStatementWrapper wrapper = executeQueryStatement( queryParameters, false, afterLoadActions, session );
		final ResultSet rs = wrapper.getResultSet();
		final Statement st = wrapper.getStatement();

// would be great to move all this below here into another method that could also be used
// from the new scrolling stuff.
//
// Would need to change the way the max-row stuff is handled (i.e. behind an interface) so
// that I could do the control breaking at the means to know when to stop

		try {
			return processResultSet(
					rs,
					queryParameters,
					session,
					returnProxies,
					forcedResultTransformer,
					maxRows,
					afterLoadActions
			);
		}
		finally {
			session.getJdbcCoordinator().getLogicalConnection().getResourceRegistry().release( st );
			session.getJdbcCoordinator().afterStatementExecution();
		}

	}
