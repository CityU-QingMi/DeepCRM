	private static void getPathText(StringBuilder buf, AST n) {
		AST firstChild = n.getFirstChild();
		// If the node has a first child, recurse into the first child.
		if ( firstChild != null ) {
			getPathText( buf, firstChild );
		}
		// Append the text of the current node.
		buf.append( n.getText() );
		// If there is a second child (RHS), recurse into that child.
		if ( firstChild != null && firstChild.getNextSibling() != null ) {
			getPathText( buf, firstChild.getNextSibling() );
		}
	}
