	@Override
	public void finishingCollectionElements(CollectionElementDefinition elementDefinition) {
		System.out.println(
				String.format(
						"%s Finishing collection elements (%s)",
						StringHelper.repeat( "<<", depth-- ),
						elementDefinition.getCollectionDefinition().getCollectionPersister().getRole()
				)
		);
	}
