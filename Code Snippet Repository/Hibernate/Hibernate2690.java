	@SuppressWarnings("")
	protected boolean isFromElementUpdateOrDeleteRoot(FromElement element) {
		if ( element.getFromClause().getParentFromClause() != null ) {
			// its not even a root...
			return false;
		}

		return getWalker().getStatementType() == HqlSqlTokenTypes.DELETE
				|| getWalker().getStatementType() == HqlSqlTokenTypes.UPDATE;
	}
