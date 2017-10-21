	@Override
	public void finishingCollection(CollectionDefinition collectionDefinition) {
		System.out.println(
				String.format(
						"%s Finishing collection (%s)",
						StringHelper.repeat( ">>", depth-- ),
						collectionDefinition.getCollectionPersister().getRole()
				)
		);
	}
