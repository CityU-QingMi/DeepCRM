	private void visitDepthFirst(AST ast) {
		if ( ast == null ) {
			return;
		}
		Deque<AST> stack = new ArrayDeque<AST>();
		stack.addLast( ast );
		while ( !stack.isEmpty() ) {
			ast = stack.removeLast();
			strategy.visit( ast );
			if ( ast.getNextSibling() != null ) {
				stack.addLast( ast.getNextSibling() );
			}
			if ( ast.getFirstChild() != null ) {
				stack.addLast( ast.getFirstChild() );
			}
		}
	}
