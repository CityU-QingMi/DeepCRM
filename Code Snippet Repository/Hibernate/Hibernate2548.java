	private AST processIsEmpty(AST node, boolean negated) {
		node.setNextSibling( null );
		// NOTE: Because we're using ASTUtil.createParent(), the tree must be created from the bottom up.
		// IS EMPTY x => (EXISTS (QUERY (SELECT_FROM (FROM x) ) ) )
		AST ast = createSubquery( node );
		ast = ASTUtil.createParent( astFactory, EXISTS, "exists", ast );
		// Add NOT if it's negated.
		if ( !negated ) {
			ast = ASTUtil.createParent( astFactory, NOT, "not", ast );
		}
		return ast;
	}
