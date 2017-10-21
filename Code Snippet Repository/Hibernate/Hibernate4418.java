	@Override
	public <Y> Fetch<X, Y> fetch(PluralAttribute<? super X, ?, Y> pluralAttribute, JoinType jt) {
		if ( !canBeFetchSource() ) {
			throw illegalFetch();
		}

		final Fetch<X, Y> fetch;
		// TODO : combine Fetch and Join hierarchies (JoinImplementor extends Join,Fetch???)
		if ( PluralAttribute.CollectionType.COLLECTION.equals( pluralAttribute.getCollectionType() ) ) {
			fetch = constructJoin( (CollectionAttribute<X, Y>) pluralAttribute, jt );
		}
		else if ( PluralAttribute.CollectionType.LIST.equals( pluralAttribute.getCollectionType() ) ) {
			fetch = constructJoin( (ListAttribute<X, Y>) pluralAttribute, jt );
		}
		else if ( PluralAttribute.CollectionType.SET.equals( pluralAttribute.getCollectionType() ) ) {
			fetch = constructJoin( (SetAttribute<X, Y>) pluralAttribute, jt );
		}
		else {
			fetch = constructJoin( (MapAttribute<X, ?, Y>) pluralAttribute, jt );
		}
		joinScope.addFetch( fetch );
		return fetch;
	}
