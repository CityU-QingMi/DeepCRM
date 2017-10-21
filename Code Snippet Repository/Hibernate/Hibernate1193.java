	protected boolean areTypeMatch(Class converterDefinedType, Class propertyType) {
		if ( converterDefinedType == null ) {
			throw new AnnotationException( "AttributeConverter defined java type cannot be null" );
		}
		if ( propertyType == null ) {
			throw new AnnotationException( "Property defined java type cannot be null" );
		}

		return converterDefinedType.equals( propertyType )
				|| PrimitiveWrapperHelper.arePrimitiveWrapperEquivalents( converterDefinedType, propertyType );
	}
