	@Override
	public <Y> Fetch<X, Y> fetch(SingularAttribute<? super X, Y> attribute, JoinType jt) {
		if ( !canBeFetchSource() ) {
			throw illegalFetch();
		}

		Fetch<X, Y> fetch = constructJoin( attribute, jt );
		joinScope.addFetch( fetch );
		return fetch;
	}
