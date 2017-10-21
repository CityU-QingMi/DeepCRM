	@Override
	protected void resolveSelectExpression(AST node) throws SemanticException {
		// This is called when it's time to fully resolve a path expression.
		int type = node.getType();
		switch ( type ) {
			case DOT: {
				DotNode dot = (DotNode) node;
				dot.resolveSelectExpression();
				break;
			}
			case ALIAS_REF: {
				// Notify the FROM element that it is being referenced by the select.
				FromReferenceNode aliasRefNode = (FromReferenceNode) node;
				//aliasRefNode.resolve( false, false, aliasRefNode.getText() ); //TODO: is it kosher to do it here?
				aliasRefNode.resolve( false, false ); //TODO: is it kosher to do it here?
				FromElement fromElement = aliasRefNode.getFromElement();
				if ( fromElement != null ) {
					fromElement.setIncludeSubclasses( true );
				}
				break;
			}
			default: {
				break;
			}
		}
	}
