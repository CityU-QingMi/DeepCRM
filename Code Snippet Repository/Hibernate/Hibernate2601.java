	private void mutateRowValueConstructorSyntax(int operandColumnSpan) {
		final int comparisonType = getType();
		final String comparisonText = getText();

		final int expansionConnectorType = getExpansionConnectorType();
		final String expansionConnectorText = getExpansionConnectorText();

		setType( expansionConnectorType );
		setText( expansionConnectorText );

		String[] mutationTexts = extractMutationTexts( getOperand(), operandColumnSpan );

		AST container = this;
		for ( int i = operandColumnSpan - 1; i > 0; i-- ) {
			if ( i == 1 ) {
				AST op1 = getASTFactory().create( comparisonType, comparisonText );
				AST operand1 = getASTFactory().create( HqlSqlTokenTypes.SQL_TOKEN, mutationTexts[0] );
				op1.setFirstChild( operand1 );
				container.setFirstChild( op1 );
				AST op2 = getASTFactory().create( comparisonType, comparisonText );
				AST operand2 = getASTFactory().create( HqlSqlTokenTypes.SQL_TOKEN, mutationTexts[1] );
				op2.setFirstChild( operand2 );
				op1.setNextSibling( op2 );
			}
			else {
				AST op = getASTFactory().create( comparisonType, comparisonText );
				AST operand = getASTFactory().create( HqlSqlTokenTypes.SQL_TOKEN, mutationTexts[i] );
				op.setFirstChild( operand );
				AST newContainer = getASTFactory().create( expansionConnectorType, expansionConnectorText );
				container.setFirstChild( newContainer );
				newContainer.setNextSibling( op );
				container = newContainer;
			}
		}
	}
