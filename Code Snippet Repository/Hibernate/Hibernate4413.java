	@Override
	@SuppressWarnings({""})
	public <X, Y> CollectionJoin<X, Y> joinCollection(String attributeName, JoinType jt) {
		final Attribute<X, ?> attribute = (Attribute<X, ?>) locateAttribute( attributeName );
		if ( !attribute.isCollection() ) {
			throw new IllegalArgumentException( "Requested attribute was not a collection" );
		}

		final PluralAttribute pluralAttribute = (PluralAttribute) attribute;
		if ( !PluralAttribute.CollectionType.COLLECTION.equals( pluralAttribute.getCollectionType() ) ) {
			throw new IllegalArgumentException( "Requested attribute was not a collection" );
		}

		return (CollectionJoin<X, Y>) join( (CollectionAttribute) attribute, jt );
	}
