	public CollectionFetchReturn(
			String alias,
			NonScalarReturn owner,
			String ownerProperty,
			CollectionAliases collectionAliases,
			EntityAliases elementEntityAliases,
			LockMode lockMode) {
		super( owner, ownerProperty, alias, lockMode );
		this.collectionAliases = collectionAliases;
		this.elementEntityAliases = elementEntityAliases;
	}
