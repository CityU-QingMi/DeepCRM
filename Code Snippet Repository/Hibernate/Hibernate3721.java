	protected CompositeAttributeFetch createCompositeAttributeFetch(
			AttributeDefinition attributeDefinition,
			ExpandingCompositeQuerySpace compositeQuerySpace) {
		return new CompositeAttributeFetchImpl(
				this,
				attributeDefinition,
				compositeQuerySpace,
				true
		);
	}
