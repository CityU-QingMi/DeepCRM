	@Override
	@SuppressWarnings( {""})
	public String getRenderText(SessionFactoryImplementor sessionFactory) {
		final boolean literalValue = getValue();

		if ( expectedType instanceof AttributeConverterTypeAdapter ) {
			return determineConvertedValue( (AttributeConverterTypeAdapter) expectedType, literalValue );
		}
		else if ( expectedType instanceof LiteralType ) {
			try {
				return ( (LiteralType) expectedType ).objectToSQLString( getValue(), sessionFactory.getDialect() );
			}
			catch( Exception t ) {
				throw new QueryException( "Unable to render boolean literal value using expected LiteralType", t );
			}
		}

		return sessionFactory.getDialect().toBooleanValueString( literalValue );
	}
