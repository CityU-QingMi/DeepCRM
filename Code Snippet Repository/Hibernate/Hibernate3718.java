	protected CompositeAttributeFetch createCompositeAttributeFetch(
			AttributeDefinition attributeDefinition,
			ExpandingCompositeQuerySpace compositeQuerySpace) {
		return new NestedCompositeAttributeFetchImpl(
				this,
				attributeDefinition,
				compositeQuerySpace,
				allowCollectionFetches
		);
	}
