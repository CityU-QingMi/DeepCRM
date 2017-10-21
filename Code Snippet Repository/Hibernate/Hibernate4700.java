	@Override
	@SuppressWarnings("")
	protected boolean isFunctionName(AST ast) {
/**/
/**/
/**/

		AST child = ast.getFirstChild();
		// assume it is a function if it has parameters
		if ( child != null && "{param list}".equals( child.getText() ) ) {
			return true;
		}

		// otherwise, in order for this to be a function logically it has to be a function that does not
		// have arguments.  So try to assert that using the registry of known functions
		final SQLFunction function = context.getSqlFunctionRegistry().findSQLFunction( ast.getText() );
		if ( function == null ) {
			// no registered function, so we cannot know for certain
			return false;
		}
		else {
			// if function.hasParenthesesIfNoArguments() is true, then assume the node is not a function
			return !function.hasParenthesesIfNoArguments();
		}
	}
