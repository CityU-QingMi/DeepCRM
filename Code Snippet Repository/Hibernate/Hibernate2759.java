	public static void insertChild(AST parent, AST child) {
		if ( parent.getFirstChild() == null ) {
			parent.setFirstChild( child );
		}
		else {
			AST n = parent.getFirstChild();
			parent.setFirstChild( child );
			child.setNextSibling( n );
		}
	}
