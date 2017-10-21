	protected CompositeAttributeFetchImpl(
			FetchSource source,
			AttributeDefinition attributeDefinition,
			ExpandingCompositeQuerySpace compositeQuerySpace,
			boolean allowCollectionFetches) {
		super(
				compositeQuerySpace,
				allowCollectionFetches,
				source.getPropertyPath().append( attributeDefinition.getName() )
		);
		this.source = source;
		this.fetchedAttribute = attributeDefinition;
	}
