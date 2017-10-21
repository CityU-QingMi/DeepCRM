	@Override
	public void finishingCollectionIndex(CollectionIndexDefinition collectionIndexDefinition) {
		System.out.println(
				String.format(
						"%s Finishing collection index (%s)",
						StringHelper.repeat( "<<", depth-- ),
						collectionIndexDefinition.getCollectionDefinition().getCollectionPersister().getRole()
				)
		);
	}
