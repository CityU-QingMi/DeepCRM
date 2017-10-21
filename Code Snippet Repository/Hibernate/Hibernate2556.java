	@Override
	protected void resolve(AST node, AST predicateNode) throws SemanticException {
		if ( node != null ) {
			// This is called when it's time to fully resolve a path expression.
			ResolvableNode r = (ResolvableNode) node;
			if ( isInFunctionCall() ) {
				r.resolveInFunctionCall( false, true );
			}
			else {
				r.resolve( false, true, null, null, predicateNode );    // Generate implicit joins, only if necessary.
			}
		}
	}
