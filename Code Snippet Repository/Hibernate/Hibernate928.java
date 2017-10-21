	private void bindDiscriminatorSubclassEntities(
			AbstractEntitySourceImpl entitySource,
			PersistentClass superEntityDescriptor) {
		for ( IdentifiableTypeSource subType : entitySource.getSubTypes() ) {
			final SingleTableSubclass subEntityDescriptor = new SingleTableSubclass( superEntityDescriptor, metadataBuildingContext );
			bindDiscriminatorSubclassEntity( (SubclassEntitySourceImpl) subType, subEntityDescriptor );
			superEntityDescriptor.addSubclass( subEntityDescriptor );
			entitySource.getLocalMetadataBuildingContext().getMetadataCollector().addEntityBinding( subEntityDescriptor );
		}
	}
