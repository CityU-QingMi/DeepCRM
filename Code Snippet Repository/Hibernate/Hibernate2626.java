	private Type[] resolveConstructorArgumentTypes() throws SemanticException {
		SelectExpression[] argumentExpressions = collectSelectExpressions();
		if ( argumentExpressions == null ) {
			// return an empty Type array
			return new Type[] {};
		}

		Type[] types = new Type[argumentExpressions.length];
		for ( int x = 0; x < argumentExpressions.length; x++ ) {
			types[x] = argumentExpressions[x].getDataType();
		}
		return types;
	}
