	@Override
	@SuppressWarnings({ "" })
	public Type custom(Class userTypeClass, Properties parameters) {
		if ( CompositeUserType.class.isAssignableFrom( userTypeClass ) ) {
			return typeResolver.getTypeFactory().customComponent( userTypeClass, parameters );
		}
		else {
			return typeResolver.getTypeFactory().custom( userTypeClass, parameters );
		}
	}
