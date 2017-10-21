	public final OrderByClause getOrderByClause() {
		if ( orderByClause == null ) {
			orderByClause = locateOrderByClause();

			// if there is no order by, make one
			if ( orderByClause == null ) {
				LOG.debug( "getOrderByClause() : Creating a new ORDER BY clause" );
				orderByClause = (OrderByClause) getWalker().getASTFactory().create( SqlTokenTypes.ORDER, "ORDER" );

				// Find the WHERE; if there is no WHERE, find the FROM...
				AST prevSibling = ASTUtil.findTypeInChildren( this, SqlTokenTypes.WHERE );
				if ( prevSibling == null ) {
					prevSibling = ASTUtil.findTypeInChildren( this, SqlTokenTypes.FROM );
				}

				// Now, inject the newly built ORDER BY into the tree
				orderByClause.setNextSibling( prevSibling.getNextSibling() );
				prevSibling.setNextSibling( orderByClause );
			}
		}
		return orderByClause;
	}
