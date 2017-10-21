	@Override
	public AnyAttributeFetch buildAnyAttributeFetch(
			AssociationAttributeDefinition attributeDefinition,
			FetchStrategy fetchStrategy) {

		final AnyAttributeFetch fetch = new AnyAttributeFetchImpl(
				this,
				attributeDefinition,
				fetchStrategy
		);
		addFetch( fetch );
		return fetch;
	}
