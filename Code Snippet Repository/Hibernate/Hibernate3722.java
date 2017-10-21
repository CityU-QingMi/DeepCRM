	@Override
	public EntityFetch buildEntityAttributeFetch(
			AssociationAttributeDefinition attributeDefinition,
			FetchStrategy fetchStrategy) {

		final ExpandingEntityQuerySpace entityQuerySpace = QuerySpaceHelper.INSTANCE.makeEntityQuerySpace(
				expandingQuerySpace(),
				attributeDefinition,
				getQuerySpaces().generateImplicitUid(),
				fetchStrategy
		);
		final EntityFetch fetch = new EntityAttributeFetchImpl( this, attributeDefinition, fetchStrategy, entityQuerySpace );
		addFetch( fetch );
		return fetch;
	}
