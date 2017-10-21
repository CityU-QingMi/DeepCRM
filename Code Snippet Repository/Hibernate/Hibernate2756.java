	public static AST findPreviousSibling(AST parent, AST child) {
		AST prev = null;
		AST n = parent.getFirstChild();
		while ( n != null ) {
			if ( n == child ) {
				return prev;
			}
			prev = n;
			n = n.getNextSibling();
		}
		throw new IllegalArgumentException( "Child not found in parent!" );
	}
