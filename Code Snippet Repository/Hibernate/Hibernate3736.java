	public NestedCompositeAttributeFetchImpl(
			FetchSource source,
			AttributeDefinition fetchedAttributeDefinition,
			ExpandingCompositeQuerySpace compositeQuerySpace,
			boolean allowCollectionFetches) {
		super(
				compositeQuerySpace,
				allowCollectionFetches,
				source.getPropertyPath().append( fetchedAttributeDefinition.getName() )
		);
		this.source = source;
		this.fetchedAttributeDefinition = fetchedAttributeDefinition;
	}
