	@Override
	public void processMemberOf(Token n, AST p, ASTPair currentAST) {
		// convert MEMBER OF to the equivalent IN ELEMENTS structure...
		AST inNode = n == null ? astFactory.create( IN, "in" ) : astFactory.create( NOT_IN, "not in" );
		astFactory.makeASTRoot( currentAST, inNode );

		AST inListNode = astFactory.create( IN_LIST, "inList" );
		inNode.addChild( inListNode );
		AST elementsNode = astFactory.create( ELEMENTS, "elements" );
		inListNode.addChild( elementsNode );
		elementsNode.addChild( p );
	}
