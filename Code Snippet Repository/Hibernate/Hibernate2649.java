	public void resolve() {
		// Make sure that all from elements registered with this FROM clause are actually in the AST.
		ASTIterator iter = new ASTIterator( this.getFirstChild() );
		Set childrenInTree = new HashSet();
		while ( iter.hasNext() ) {
			childrenInTree.add( iter.next() );
		}
		for ( FromElement fromElement : fromElements ) {
			if ( !childrenInTree.contains( fromElement ) ) {
				throw new IllegalStateException( "Element not in AST: " + fromElement );
			}
		}
	}
