	public CollectionReturnImpl(CollectionDefinition collectionDefinition, ExpandingQuerySpaces querySpaces) {
		super(
				querySpaces.makeRootCollectionQuerySpace(
						querySpaces.generateImplicitUid(),
						collectionDefinition.getCollectionPersister()
				),
				new PropertyPath( "[" + collectionDefinition.getCollectionPersister().getRole() + "]" ),
				true
		);
	}
