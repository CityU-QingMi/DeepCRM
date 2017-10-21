	public static AST createTree(ASTFactory factory, AST[] nestedChildren) {
		AST[] array = new AST[2];
		int limit = nestedChildren.length - 1;
		for ( int i = limit; i >= 0; i-- ) {
			if ( i != limit ) {
				array[1] = nestedChildren[i + 1];
				array[0] = nestedChildren[i];
				factory.make( array );
			}
		}
		return array[0];
	}
