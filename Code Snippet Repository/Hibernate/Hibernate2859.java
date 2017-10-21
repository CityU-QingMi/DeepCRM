	private String quoteIdentifier(Object value, Type type) {
		Type resolvedType = ( !type.getReturnedClass().equals( value.getClass() ) ) ?
			typeResolver.heuristicType( value.getClass().getName() ) : type;

		if ( resolvedType instanceof LiteralType ) {
			LiteralType literalType = (LiteralType) resolvedType;
			try {
				return literalType.objectToSQLString( value, dialect );
			}
			catch ( Exception e ) {
				throw new IllegalArgumentException( e );
			}
		}
		return String.valueOf( value );
	}
