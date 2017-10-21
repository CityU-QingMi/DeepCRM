	protected int doExecute(QueryParameters parameters, SharedSessionContractImplementor session, String sql,
			List parameterSpecifications) throws HibernateException {
		BulkOperationCleanupAction action = new BulkOperationCleanupAction( session, persister );
		if ( session.isEventSource() ) {
			( (EventSource) session ).getActionQueue().addAction( action );
		}
		else {
			action.getAfterTransactionCompletionProcess().doAfterTransactionCompletion( true, session );
		}

		PreparedStatement st = null;
		RowSelection selection = parameters.getRowSelection();

		try {
			try {
				st = session.getJdbcCoordinator().getStatementPreparer().prepareStatement( sql, false );
				Iterator paramSpecItr = parameterSpecifications.iterator();
				int pos = 1;
				while ( paramSpecItr.hasNext() ) {
					final ParameterSpecification paramSpec = (ParameterSpecification) paramSpecItr.next();
					pos += paramSpec.bind( st, parameters, session, pos );
				}
				if ( selection != null ) {
					if ( selection.getTimeout() != null ) {
						st.setQueryTimeout( selection.getTimeout() );
					}
				}

				return session.getJdbcCoordinator().getResultSetReturn().executeUpdate( st );
			}
			finally {
				if ( st != null ) {
					session.getJdbcCoordinator().getLogicalConnection().getResourceRegistry().release( st );
					session.getJdbcCoordinator().afterStatementExecution();
				}
			}
		}
		catch( SQLException sqle ) {
			throw session.getJdbcServices().getSqlExceptionHelper().convert( sqle, "could not execute update query", sql );
		}
	}
