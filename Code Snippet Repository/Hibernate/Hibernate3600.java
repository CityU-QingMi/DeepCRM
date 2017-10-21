	public CollectionReturn(
			String alias,
			String ownerEntityName,
			String ownerProperty,
			CollectionAliases collectionAliases,
			EntityAliases elementEntityAliases,
			LockMode lockMode) {
		super( alias, lockMode );
		this.ownerEntityName = ownerEntityName;
		this.ownerProperty = ownerProperty;
		this.collectionAliases = collectionAliases;
		this.elementEntityAliases = elementEntityAliases;
	}
