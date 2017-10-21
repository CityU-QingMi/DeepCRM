		@Override
		public void visit(AST node) {
			if ( dotRoot != null ) {
				// we are already processing a dot-structure
				if ( ASTUtil.isSubtreeChild( dotRoot, node ) ) {
					return;
				}
				// we are now at a new tree level
				dotRoot = null;
			}

			if ( node.getType() == HqlTokenTypes.DOT ) {
				dotRoot = node;
				handleDotStructure( dotRoot );
			}
		}
