	@Override
	@SuppressWarnings({ "" })
	public CollectionAttribute<? super X, ?> getCollection(String name) {
		PluralAttribute<? super X, ?, ?> attribute = getPluralAttribute( name );
		if ( attribute == null && getSupertype() != null ) {
			attribute = getSupertype().getPluralAttribute( name );
		}
		basicCollectionCheck( attribute, name );
		return ( CollectionAttribute<X, ?> ) attribute;
	}
