	@Override
	@SuppressWarnings({""})
	public <X, Y> ListJoin<X, Y> joinList(String attributeName, JoinType jt) {
		final Attribute<X, ?> attribute = (Attribute<X, ?>) locateAttribute( attributeName );
		if ( !attribute.isCollection() ) {
			throw new IllegalArgumentException( "Requested attribute was not a list" );
		}

		final PluralAttribute pluralAttribute = (PluralAttribute) attribute;
		if ( !PluralAttribute.CollectionType.LIST.equals( pluralAttribute.getCollectionType() ) ) {
			throw new IllegalArgumentException( "Requested attribute was not a list" );
		}

		return (ListJoin<X, Y>) join( (ListAttribute) attribute, jt );
	}
