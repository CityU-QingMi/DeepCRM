	public static boolean isSubtreeChild(AST fixture, AST test) {
		AST n = fixture.getFirstChild();
		while ( n != null ) {
			if ( n == test ) {
				return true;
			}
			if ( n.getFirstChild() != null && isSubtreeChild( n, test ) ) {
				return true;
			}
			n = n.getNextSibling();
		}
		return false;
	}
