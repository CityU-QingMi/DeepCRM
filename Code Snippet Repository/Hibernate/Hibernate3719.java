	@Override
	public CollectionAttributeFetch buildCollectionAttributeFetch(
			AssociationAttributeDefinition attributeDefinition,
			FetchStrategy fetchStrategy) {
		if ( !allowCollectionFetches ) {
			throw new WalkingException(
					String.format(
							"This composite path [%s] does not allow collection fetches (composite id or composite collection index/element",
							getPropertyPath().getFullPath()
					)
			);
		}
		return super.buildCollectionAttributeFetch( attributeDefinition, fetchStrategy );
	}
