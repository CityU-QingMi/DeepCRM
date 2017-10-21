	public static AST createBinarySubtree(
			ASTFactory factory,
			int parentType,
			String parentText,
			AST child1,
			AST child2) {
		ASTArray array = createAstArray( factory, 3, parentType, parentText, child1 );
		array.add( child2 );
		return factory.make( array );
	}
