	private static boolean isFunction(String lcToken, String nextToken, SQLFunctionRegistry functionRegistry) {
		// checking for "(" is currently redundant because it is checked before getting here;
		// doing the check anyhow, in case that earlier check goes away;
		if ( "(".equals( nextToken ) ) {
			return true;
		}
		SQLFunction function = functionRegistry.findSQLFunction(lcToken);
		if ( function == null ) {
			// lcToken does not refer to a function
			return false;
		}
		// if function.hasParenthesesIfNoArguments() is true, then assume
		// lcToken is not a function (since it is not followed by '(')
		return ! function.hasParenthesesIfNoArguments();
	}
