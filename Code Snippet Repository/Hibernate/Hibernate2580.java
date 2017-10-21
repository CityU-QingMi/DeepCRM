	private StatementExecutor buildAppropriateStatementExecutor(HqlSqlWalker walker) {
		final Statement statement = (Statement) walker.getAST();
		if ( walker.getStatementType() == HqlSqlTokenTypes.DELETE ) {
			final FromElement fromElement = walker.getFinalFromClause().getFromElement();
			final Queryable persister = fromElement.getQueryable();
			if ( persister.isMultiTable() ) {
				return new MultiTableDeleteExecutor( walker );
			}
			else {
				return new DeleteExecutor( walker, persister );
			}
		}
		else if ( walker.getStatementType() == HqlSqlTokenTypes.UPDATE ) {
			final FromElement fromElement = walker.getFinalFromClause().getFromElement();
			final Queryable persister = fromElement.getQueryable();
			if ( persister.isMultiTable() ) {
				// even here, if only properties mapped to the "base table" are referenced
				// in the set and where clauses, this could be handled by the BasicDelegate.
				// TODO : decide if it is better performance-wise to doAfterTransactionCompletion that check, or to simply use the MultiTableUpdateDelegate
				return new MultiTableUpdateExecutor( walker );
			}
			else {
				return new BasicExecutor( walker, persister );
			}
		}
		else if ( walker.getStatementType() == HqlSqlTokenTypes.INSERT ) {
			return new BasicExecutor( walker, ( (InsertStatement) statement ).getIntoClause().getQueryable() );
		}
		else {
			throw new QueryException( "Unexpected statement type" );
		}
	}
