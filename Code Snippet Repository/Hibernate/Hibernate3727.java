	public AnyAttributeFetchImpl(
			FetchSource fetchSource,
			AssociationAttributeDefinition fetchedAttribute,
			FetchStrategy fetchStrategy) {
		super( fetchSource.getPropertyPath().append( fetchedAttribute.getName() ) );

		this.fetchSource = fetchSource;
		this.fetchedAttribute = fetchedAttribute;
		this.fetchStrategy = fetchStrategy;
	}
