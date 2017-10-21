	private void mutateRowValueConstructorSyntax(int valueElements) {
		// mutation depends on the types of nodes involved...
		int comparisonType = getType();
		String comparisonText = getText();

		switch ( comparisonType ) {
			case HqlSqlTokenTypes.EQ:
				setType( HqlSqlTokenTypes.AND );
				setText( "AND" );
				break;

			case HqlSqlTokenTypes.NE:
				setType( HqlSqlTokenTypes.OR );
				setText( "OR" );
				break;

			default:
				throw new QuerySyntaxException( comparisonText + " operator not supported on composite types." );
		}

		String[] lhsElementTexts = extractMutationTexts( getLeftHandOperand(), valueElements );
		String[] rhsElementTexts = extractMutationTexts( getRightHandOperand(), valueElements );

		ParameterSpecification lhsEmbeddedCompositeParameterSpecification =
				getLeftHandOperand() == null || ( !ParameterNode.class.isInstance( getLeftHandOperand() ) )
						? null
						: ( (ParameterNode) getLeftHandOperand() ).getHqlParameterSpecification();

		ParameterSpecification rhsEmbeddedCompositeParameterSpecification =
				getRightHandOperand() == null || ( !ParameterNode.class.isInstance( getRightHandOperand() ) )
						? null
						: ( (ParameterNode) getRightHandOperand() ).getHqlParameterSpecification();

		translate(
				valueElements,
				comparisonType,
				comparisonText,
				lhsElementTexts,
				rhsElementTexts,
				lhsEmbeddedCompositeParameterSpecification,
				rhsEmbeddedCompositeParameterSpecification,
				this
		);
	}
