	@Override
	public CompositeAttributeFetch buildCompositeAttributeFetch(
			AttributeDefinition attributeDefinition) {
		final ExpandingCompositeQuerySpace compositeQuerySpace = QuerySpaceHelper.INSTANCE.makeCompositeQuerySpace(
				expandingQuerySpace(),
				attributeDefinition,
				getQuerySpaces().generateImplicitUid(),
				true
		);

		final CompositeAttributeFetch fetch = createCompositeAttributeFetch( attributeDefinition, compositeQuerySpace );
		addFetch( fetch );
		return fetch;
	}
