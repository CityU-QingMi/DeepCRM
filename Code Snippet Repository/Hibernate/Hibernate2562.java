	@Override
	protected boolean isOrderExpressionResultVariableRef(AST orderExpressionNode) throws SemanticException {
		// ORDER BY is not supported in a subquery
		// TODO: should an exception be thrown if an ORDER BY is in a subquery?
		if ( !isSubQuery() &&
				orderExpressionNode.getType() == IDENT &&
				selectExpressionsByResultVariable.containsKey( orderExpressionNode.getText() ) ) {
			return true;
		}
		return false;
	}
