	@SuppressWarnings("")
	@Override
	protected AST resolveFunction(AST ast) {
/**/
/**/
/**/
		AST child = ast.getFirstChild();
		if ( child != null ) {
			assert "{param list}".equals( child.getText() );
			child = child.getFirstChild();
		}

		final String functionName = ast.getText();
		final SQLFunction function = context.getSqlFunctionRegistry().findSQLFunction( functionName );
		if ( function == null ) {
			String text = functionName;
			if ( child != null ) {
				text += '(';
				while ( child != null ) {
					text += resolveFunctionArgument( child );
					child = child.getNextSibling();
					if ( child != null ) {
						text += ", ";
					}
				}
				text += ')';
			}
			return getASTFactory().create( OrderByTemplateTokenTypes.IDENT, text );
		}
		else {
			ArrayList expressions = new ArrayList();
			while ( child != null ) {
				expressions.add( resolveFunctionArgument( child ) );
				child = child.getNextSibling();
			}
			final String text = function.render( null, expressions, context.getSessionFactory() );
			return getASTFactory().create( OrderByTemplateTokenTypes.IDENT, text );
		}
	}
