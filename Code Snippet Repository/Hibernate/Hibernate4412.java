	@Override
	@SuppressWarnings({""})
	public <X, Y> Join<X, Y> join(String attributeName, JoinType jt) {
		if ( !canBeJoinSource() ) {
			throw illegalJoin();
		}

		if ( jt.equals( JoinType.RIGHT ) ) {
			throw new UnsupportedOperationException( "RIGHT JOIN not supported" );
		}

		final Attribute<X, ?> attribute = (Attribute<X, ?>) locateAttribute( attributeName );
		if ( attribute.isCollection() ) {
			final PluralAttribute pluralAttribute = (PluralAttribute) attribute;
			if ( PluralAttribute.CollectionType.COLLECTION.equals( pluralAttribute.getCollectionType() ) ) {
				return (Join<X, Y>) join( (CollectionAttribute) attribute, jt );
			}
			else if ( PluralAttribute.CollectionType.LIST.equals( pluralAttribute.getCollectionType() ) ) {
				return (Join<X, Y>) join( (ListAttribute) attribute, jt );
			}
			else if ( PluralAttribute.CollectionType.SET.equals( pluralAttribute.getCollectionType() ) ) {
				return (Join<X, Y>) join( (SetAttribute) attribute, jt );
			}
			else {
				return (Join<X, Y>) join( (MapAttribute) attribute, jt );
			}
		}
		else {
			return (Join<X, Y>) join( (SingularAttribute) attribute, jt );
		}
	}
