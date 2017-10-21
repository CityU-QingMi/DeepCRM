	@Override
	@SuppressWarnings({ "" })
	public <E> CollectionAttribute<? super X, E> getCollection(String name, Class<E> elementType) {
		PluralAttribute<? super X, ?, ?> attribute = declaredPluralAttributes.get( name );
		if ( attribute == null && getSupertype() != null ) {
			attribute = getSupertype().getPluralAttribute( name );
		}
		checkCollectionElementType( attribute, name, elementType );
		return ( CollectionAttribute<? super X, E> ) attribute;
	}
