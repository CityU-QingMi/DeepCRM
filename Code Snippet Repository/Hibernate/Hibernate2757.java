	public static void makeSiblingOfParent(AST parent, AST child) {
		AST prev = findPreviousSibling( parent, child );
		if ( prev != null ) {
			prev.setNextSibling( child.getNextSibling() );
		}
		else { // child == parent.getFirstChild()
			parent.setFirstChild( child.getNextSibling() );
		}
		child.setNextSibling( parent.getNextSibling() );
		parent.setNextSibling( child );
	}
