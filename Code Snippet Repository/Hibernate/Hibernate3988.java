	@Override
	@SuppressWarnings({ "" })
	public <E> ListAttribute<? super X, E> getList(String name, Class<E> elementType) {
		PluralAttribute<? super X, ?, ?> attribute = declaredPluralAttributes.get( name );
		if ( attribute == null && getSupertype() != null ) {
			attribute = getSupertype().getPluralAttribute( name );
		}
		checkListElementType( attribute, name, elementType );
		return ( ListAttribute<? super X, E> ) attribute;
	}
