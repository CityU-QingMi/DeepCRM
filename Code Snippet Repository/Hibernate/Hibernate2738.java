	private void renderNonScalarSelects(SelectExpression[] selectExpressions, FromClause currentFromClause)
			throws SemanticException {
		ASTAppender appender = new ASTAppender( getASTFactory(), this );
		final int size = selectExpressions.length;
		int nonscalarSize = 0;
		for ( int i = 0; i < size; i++ ) {
			if ( !selectExpressions[i].isScalar() ) {
				nonscalarSize++;
			}
		}

		int j = 0;
		for ( int i = 0; i < size; i++ ) {
			if ( !selectExpressions[i].isScalar() ) {
				SelectExpression expr = selectExpressions[i];
				FromElement fromElement = expr.getFromElement();
				if ( fromElement != null ) {
					renderNonScalarIdentifiers( fromElement, nonscalarSize, j, expr, appender );
					j++;
				}
			}
		}

		if ( !currentFromClause.isSubQuery() ) {
			// Generate the property select tokens.
			int k = 0;
			for ( int i = 0; i < size; i++ ) {
				if ( !selectExpressions[i].isScalar() ) {
					FromElement fromElement = selectExpressions[i].getFromElement();
					if ( fromElement != null ) {
						renderNonScalarProperties( appender, selectExpressions[i], fromElement, nonscalarSize, k );
						k++;
					}
				}
			}
		}
	}
