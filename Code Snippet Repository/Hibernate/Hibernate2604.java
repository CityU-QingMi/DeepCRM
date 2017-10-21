	@Override
	public final AST getWhereClause() {
		if ( whereClause == null ) {
			whereClause = locateWhereClause();
			// If there is no WHERE node, make one.
			if ( whereClause == null ) {
				getLog().debug( "getWhereClause() : Creating a new WHERE clause..." );
				whereClause = getWalker().getASTFactory().create( HqlSqlTokenTypes.WHERE, "WHERE" );
				// inject the WHERE after the parent
				AST parent = ASTUtil.findTypeInChildren( this, getWhereClauseParentTokenType() );
				whereClause.setNextSibling( parent.getNextSibling() );
				parent.setNextSibling( whereClause );
			}
		}
		return whereClause;
	}
