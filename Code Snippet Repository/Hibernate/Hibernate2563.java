	@Override
	protected void handleResultVariableRef(AST resultVariableRef) throws SemanticException {
		if ( isSubQuery() ) {
			throw new SemanticException(
					"References to result variables in subqueries are not supported."
			);
		}
		( (ResultVariableRefNode) resultVariableRef ).setSelectExpression(
				selectExpressionsByResultVariable.get( resultVariableRef.getText() )
		);
	}
