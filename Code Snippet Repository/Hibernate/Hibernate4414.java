	@Override
	@SuppressWarnings({""})
	public <X, Y> SetJoin<X, Y> joinSet(String attributeName, JoinType jt) {
		final Attribute<X, ?> attribute = (Attribute<X, ?>) locateAttribute( attributeName );
		if ( !attribute.isCollection() ) {
			throw new IllegalArgumentException( "Requested attribute was not a set" );
		}

		final PluralAttribute pluralAttribute = (PluralAttribute) attribute;
		if ( !PluralAttribute.CollectionType.SET.equals( pluralAttribute.getCollectionType() ) ) {
			throw new IllegalArgumentException( "Requested attribute was not a set" );
		}

		return (SetJoin<X, Y>) join( (SetAttribute) attribute, jt );
	}
