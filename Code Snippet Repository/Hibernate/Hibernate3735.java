	public EntityAttributeFetchImpl(
			ExpandingFetchSource fetchSource,
			AssociationAttributeDefinition fetchedAttribute,
			FetchStrategy fetchStrategy,
			ExpandingEntityQuerySpace entityQuerySpace) {
		super(
				entityQuerySpace,
				fetchSource.getPropertyPath().append( fetchedAttribute.getName() )
		);

		this.fetchSource = fetchSource;
		this.fetchedAttribute = fetchedAttribute;
		this.fetchStrategy = fetchStrategy;
	}
