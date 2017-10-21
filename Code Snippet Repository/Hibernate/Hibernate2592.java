	@Override
	protected void nestedFromFragment(AST d, AST parent) {
		// check a set of parent/child nodes in the from-clause tree
		// to determine if a comma is required between them
		if ( d != null && hasText( d ) ) {
			if ( parent != null && hasText( parent ) ) {
				// again, both should be FromElements
				FromElement left = (FromElement) parent;
				FromElement right = (FromElement) d;
				if ( right.getRealOrigin() == left ) {
					// right represents a joins originating from left...
					if ( right.getJoinSequence() != null && right.getJoinSequence().isThetaStyle() ) {
						writeCrossJoinSeparator();
					}
					else {
						out( " " );
					}
				}
				else {
					// not so sure this is even valid subtree.  but if it was, it'd
					// represent two unrelated table references...
					writeCrossJoinSeparator();
				}
			}
			out( d );
		}
	}
