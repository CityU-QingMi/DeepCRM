	@Override
	public void startingCollectionElements(CollectionElementDefinition elementDefinition) {
		System.out.println(
				String.format(
						"%s Starting collection elements (%s)",
						StringHelper.repeat( ">>", ++depth ),
						elementDefinition.getCollectionDefinition().getCollectionPersister().getRole()
				)
		);
	}
