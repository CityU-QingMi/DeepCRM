	@Override
	public void startingCollectionIndex(CollectionIndexDefinition collectionIndexDefinition) {
		System.out.println(
				String.format(
						"%s Starting collection index (%s)",
						StringHelper.repeat( ">>", ++depth ),
						collectionIndexDefinition.getCollectionDefinition().getCollectionPersister().getRole()
				)
		);
	}
