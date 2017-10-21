	@Override
	public void startingCollection(CollectionDefinition collectionDefinition) {
		System.out.println(
				String.format(
						"%s Starting collection (%s)",
						StringHelper.repeat( ">>", ++depth ),
						collectionDefinition.getCollectionPersister().getRole()
				)
		);
	}
