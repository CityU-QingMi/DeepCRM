	private static boolean isFunctionOrKeyword(
			String lcToken,
			String nextToken,
			Dialect dialect,
			SQLFunctionRegistry functionRegistry) {
		return "(".equals( nextToken ) ||
				KEYWORDS.contains( lcToken ) ||
				isType( lcToken, dialect ) ||
				isFunction( lcToken, nextToken, functionRegistry ) ||
				dialect.getKeywords().contains( lcToken ) ||
				FUNCTION_KEYWORDS.contains( lcToken );
	}
