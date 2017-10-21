	@Override
	@SuppressWarnings({""})
	public <X, Y> Fetch<X, Y> fetch(String attributeName, JoinType jt) {
		if ( !canBeFetchSource() ) {
			throw illegalFetch();
		}

		Attribute<X, ?> attribute = (Attribute<X, ?>) locateAttribute( attributeName );
		if ( attribute.isCollection() ) {
			return (Fetch<X, Y>) fetch( (PluralAttribute) attribute, jt );
		}
		else {
			return (Fetch<X, Y>) fetch( (SingularAttribute) attribute, jt );
		}
	}
