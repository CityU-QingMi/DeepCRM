	@Override
	public BasicType basic(Class javaType) {
		BasicType type = typeResolver.basic( javaType.getName() );
		if ( type == null ) {
			final Class variant = resolvePrimitiveOrPrimitiveWrapperVariantJavaType( javaType );
			if ( variant != null ) {
				type = typeResolver.basic( variant.getName() );
			}
		}
		return type;
	}
