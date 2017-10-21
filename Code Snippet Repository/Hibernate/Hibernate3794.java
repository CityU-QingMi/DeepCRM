	public CollectionReferenceAliasesImpl(
			String tableAlias,
			String manyToManyAssociationTableAlias,
			CollectionAliases collectionAliases,
			EntityReferenceAliases entityElementAliases) {
		this.tableAlias = tableAlias;
		this.manyToManyAssociationTableAlias = manyToManyAssociationTableAlias;
		this.collectionAliases = collectionAliases;
		this.entityElementAliases = entityElementAliases;
	}
