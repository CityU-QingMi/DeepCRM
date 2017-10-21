	public GeneratedCollectionAliases(Map userProvidedAliases, CollectionPersister persister, String suffix) {
		this.suffix = suffix;
		this.userProvidedAliases = userProvidedAliases;

		this.keyAliases = getUserProvidedAliases(
				"key",
				persister.getKeyColumnAliases( suffix )
		);

		this.indexAliases = getUserProvidedAliases(
				"index",
				persister.getIndexColumnAliases( suffix )
		);

		this.elementAliases = getUserProvidedAliases(
				"element",
				persister.getElementColumnAliases( suffix )
		);

		this.identifierAlias = getUserProvidedAlias(
				"id",
				persister.getIdentifierColumnAlias( suffix )
		);
	}
