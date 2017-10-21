	protected void translate(
			int valueElements, int comparisonType,
			String comparisonText, String[] lhsElementTexts,
			String[] rhsElementTexts,
			ParameterSpecification lhsEmbeddedCompositeParameterSpecification,
			ParameterSpecification rhsEmbeddedCompositeParameterSpecification,
			AST container) {
		for ( int i = valueElements - 1; i > 0; i-- ) {
			if ( i == 1 ) {
				AST op1 = getASTFactory().create( comparisonType, comparisonText );
				AST lhs1 = getASTFactory().create( HqlSqlTokenTypes.SQL_TOKEN, lhsElementTexts[0] );
				AST rhs1 = getASTFactory().create( HqlSqlTokenTypes.SQL_TOKEN, rhsElementTexts[0] );
				op1.setFirstChild( lhs1 );
				lhs1.setNextSibling( rhs1 );
				container.setFirstChild( op1 );
				AST op2 = getASTFactory().create( comparisonType, comparisonText );
				AST lhs2 = getASTFactory().create( HqlSqlTokenTypes.SQL_TOKEN, lhsElementTexts[1] );
				AST rhs2 = getASTFactory().create( HqlSqlTokenTypes.SQL_TOKEN, rhsElementTexts[1] );
				op2.setFirstChild( lhs2 );
				lhs2.setNextSibling( rhs2 );
				op1.setNextSibling( op2 );

				// "pass along" our initial embedded parameter node(s) to the first generated
				// sql fragment so that it can be handled later for parameter binding...
				SqlFragment fragment = (SqlFragment) lhs1;
				if ( lhsEmbeddedCompositeParameterSpecification != null ) {
					fragment.addEmbeddedParameter( lhsEmbeddedCompositeParameterSpecification );
				}
				if ( rhsEmbeddedCompositeParameterSpecification != null ) {
					fragment.addEmbeddedParameter( rhsEmbeddedCompositeParameterSpecification );
				}
			}
			else {
				AST op = getASTFactory().create( comparisonType, comparisonText );
				AST lhs = getASTFactory().create( HqlSqlTokenTypes.SQL_TOKEN, lhsElementTexts[i] );
				AST rhs = getASTFactory().create( HqlSqlTokenTypes.SQL_TOKEN, rhsElementTexts[i] );
				op.setFirstChild( lhs );
				lhs.setNextSibling( rhs );
				AST newContainer = getASTFactory().create( container.getType(), container.getText() );
				container.setFirstChild( newContainer );
				newContainer.setNextSibling( op );
				container = newContainer;
			}
		}
	}
