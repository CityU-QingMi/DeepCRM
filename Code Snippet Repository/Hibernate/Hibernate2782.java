	public Type findFunctionReturnType(String functionName, SQLFunction sqlFunction, AST firstArgument) {
		// determine the type of the first argument...
		Type argumentType = null;
		if ( firstArgument != null ) {
			if ( "cast".equals( functionName ) ) {
				argumentType = sfi.getTypeResolver().heuristicType( firstArgument.getNextSibling().getText() );
			}
			else if ( SqlNode.class.isInstance( firstArgument ) ) {
				argumentType = ( (SqlNode) firstArgument ).getDataType();
			}
		}

		return sqlFunction.getReturnType( argumentType, sfi );
	}
