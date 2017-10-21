	public void resolve(boolean inSelect) throws SemanticException {
		// Get the function name node.
		AST nameNode = getFirstChild();
		AST exprListNode = nameNode.getNextSibling();

		initializeMethodNode( nameNode, inSelect );

		// If the expression list has exactly one expression, and the type of the expression is a collection
		// then this might be a collection function, such as index(c) or size(c).
		if ( ASTUtil.hasExactlyOneChild( exprListNode ) ) {
			if ( "type".equals( methodName ) ) {
				typeDiscriminator( exprListNode.getFirstChild() );
				return;
			}
			if ( isCollectionPropertyMethod() ) {
				collectionProperty( exprListNode.getFirstChild(), nameNode );
				return;
			}
		}

		dialectFunction( exprListNode );
	}
