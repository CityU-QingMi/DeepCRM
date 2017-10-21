	public CollectionAttributeFetchImpl(
			ExpandingFetchSource fetchSource,
			AssociationAttributeDefinition fetchedAttribute,
			FetchStrategy fetchStrategy,
			ExpandingCollectionQuerySpace collectionQuerySpace) {
		super(
				collectionQuerySpace,
				fetchSource.getPropertyPath().append( fetchedAttribute.getName() ),
				QuerySpaceHelper.INSTANCE.shouldIncludeJoin( fetchStrategy )

		);

		this.fetchSource = fetchSource;
		this.fetchedAttribute = fetchedAttribute;
		this.fetchStrategy = fetchStrategy;
	}
