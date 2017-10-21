	@Override
	@SuppressWarnings({""})
	public <X, K, V> MapJoin<X, K, V> joinMap(String attributeName, JoinType jt) {
		final Attribute<X, ?> attribute = (Attribute<X, ?>) locateAttribute( attributeName );
		if ( !attribute.isCollection() ) {
			throw new IllegalArgumentException( "Requested attribute was not a map" );
		}

		final PluralAttribute pluralAttribute = (PluralAttribute) attribute;
		if ( !PluralAttribute.CollectionType.MAP.equals( pluralAttribute.getCollectionType() ) ) {
			throw new IllegalArgumentException( "Requested attribute was not a map" );
		}

		return (MapJoin<X, K, V>) join( (MapAttribute) attribute, jt );
	}
