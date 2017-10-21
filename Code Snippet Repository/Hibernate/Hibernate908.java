	public void bindEntityHierarchy(EntityHierarchySourceImpl hierarchySource) {
		final RootClass rootEntityDescriptor = new RootClass( metadataBuildingContext );
		bindRootEntity( hierarchySource, rootEntityDescriptor );
		hierarchySource.getRoot()
				.getLocalMetadataBuildingContext()
				.getMetadataCollector()
				.addEntityBinding( rootEntityDescriptor );

		switch ( hierarchySource.getHierarchyInheritanceType() ) {
			case NO_INHERITANCE: {
				// nothing to do
				break;
			}
			case DISCRIMINATED: {
				bindDiscriminatorSubclassEntities( hierarchySource.getRoot(), rootEntityDescriptor );
				break;
			}
			case JOINED: {
				bindJoinedSubclassEntities( hierarchySource.getRoot(), rootEntityDescriptor );
				break;
			}
			case UNION: {
				bindUnionSubclassEntities( hierarchySource.getRoot(), rootEntityDescriptor );
				break;
			}
		}
	}
