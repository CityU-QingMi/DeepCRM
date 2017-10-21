	private static Type resolveTypeVariable(TypeVariable typeVariable, ParameterizedType context) {
		Class clazz = extractClass( context.getRawType() );
		TypeVariable[] typeParameters = clazz.getTypeParameters();
		for ( int idx = 0; idx < typeParameters.length; idx++ ) {
			if ( typeVariable.getName().equals( typeParameters[idx].getName() ) ) {
				return resolveType( context.getActualTypeArguments()[idx], context );
			}
		}
		return typeVariable;
	}
