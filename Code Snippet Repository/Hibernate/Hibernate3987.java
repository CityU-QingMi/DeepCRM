	@Override
	@SuppressWarnings({ "" })
	public <E> SetAttribute<? super X, E> getSet(String name, Class<E> elementType) {
		PluralAttribute<? super X, ?, ?> attribute = declaredPluralAttributes.get( name );
		if ( attribute == null && getSupertype() != null ) {
			attribute = getSupertype().getPluralAttribute( name );
		}
		checkSetElementType( attribute, name, elementType );
		return ( SetAttribute<? super X, E> ) attribute;
	}
