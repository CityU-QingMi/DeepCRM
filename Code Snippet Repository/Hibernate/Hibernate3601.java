	public ColumnCollectionAliases(Map userProvidedAliases, SQLLoadableCollection persister) {
		this.userProvidedAliases = userProvidedAliases;

		this.keyAliases = getUserProvidedAliases(
				"key",
				persister.getKeyColumnNames()
		);

		this.indexAliases = getUserProvidedAliases(
				"index",
				persister.getIndexColumnNames()
		);

		this.elementAliases = getUserProvidedAliases(
				"element",
				persister.getElementColumnNames()
		);

		this.identifierAlias = getUserProvidedAlias(
				"id",
				persister.getIdentifierColumnName()
		);

	}
