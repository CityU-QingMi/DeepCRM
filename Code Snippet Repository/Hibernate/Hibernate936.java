	private void bindUnionSubclassEntities(
			EntitySource entitySource,
			PersistentClass superEntityDescriptor) {
		for ( IdentifiableTypeSource subType : entitySource.getSubTypes() ) {
			final UnionSubclass subEntityDescriptor = new UnionSubclass( superEntityDescriptor, metadataBuildingContext );
			bindUnionSubclassEntity( (SubclassEntitySourceImpl) subType, subEntityDescriptor );
			superEntityDescriptor.addSubclass( subEntityDescriptor );
			entitySource.getLocalMetadataBuildingContext().getMetadataCollector().addEntityBinding( subEntityDescriptor );
		}
	}
