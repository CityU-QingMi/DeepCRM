	private void bindJoinedSubclassEntities(
			AbstractEntitySourceImpl entitySource,
			PersistentClass superEntityDescriptor) {
		for ( IdentifiableTypeSource subType : entitySource.getSubTypes() ) {
			final JoinedSubclass subEntityDescriptor = new JoinedSubclass( superEntityDescriptor, metadataBuildingContext );
			bindJoinedSubclassEntity( (JoinedSubclassEntitySourceImpl) subType, subEntityDescriptor );
			superEntityDescriptor.addSubclass( subEntityDescriptor );
			entitySource.getLocalMetadataBuildingContext().getMetadataCollector().addEntityBinding( subEntityDescriptor );
		}
	}
