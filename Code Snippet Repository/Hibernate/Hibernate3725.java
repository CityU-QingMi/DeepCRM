	@Override
	public CollectionAttributeFetch buildCollectionAttributeFetch(
			AssociationAttributeDefinition attributeDefinition,
			FetchStrategy fetchStrategy) {

		final ExpandingCollectionQuerySpace collectionQuerySpace = QuerySpaceHelper.INSTANCE.makeCollectionQuerySpace(
				querySpace,
				attributeDefinition,
				getQuerySpaces().generateImplicitUid(),
				fetchStrategy
		);

		final CollectionAttributeFetch fetch = new CollectionAttributeFetchImpl(
				this,
				attributeDefinition,
				fetchStrategy,
				collectionQuerySpace
		);
		addFetch( fetch );
		return fetch;
	}
