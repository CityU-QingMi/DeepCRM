	private void setPropertyNameAndPath(AST parent) {
		if ( isDotNode( parent ) ) {
			DotNode dotNode = (DotNode) parent;
			AST lhs = dotNode.getFirstChild();
			AST rhs = lhs.getNextSibling();
			propertyName = rhs.getText();
			propertyPath = propertyPath + "." + propertyName; // Append the new property name onto the unresolved path.
			dotNode.propertyPath = propertyPath;
			LOG.debugf( "Unresolved property path is now '%s'", dotNode.propertyPath );
		}
		else {
			LOG.debugf( "Terminal getPropertyPath = [%s]", propertyPath );
		}
	}
