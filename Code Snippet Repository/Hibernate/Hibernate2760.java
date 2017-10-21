	private static ASTArray createAstArray(
			ASTFactory factory,
			int size,
			int parentType,
			String parentText,
			AST child1) {
		ASTArray array = new ASTArray( size );
		array.add( factory.create( parentType, parentText ) );
		array.add( child1 );
		return array;
	}
