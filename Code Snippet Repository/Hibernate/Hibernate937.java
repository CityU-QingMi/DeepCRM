	private void bindUnionSubclassEntity(
			SubclassEntitySourceImpl entitySource,
			UnionSubclass entityDescriptor) {
		MappingDocument mappingDocument = entitySource.sourceMappingDocument();

		bindBasicEntityValues(
				mappingDocument,
				entitySource,
				entityDescriptor
		);

		final Table primaryTable = bindEntityTableSpecification(
				mappingDocument,
				entitySource.getPrimaryTable(),
				entityDescriptor.getSuperclass().getTable(),
				entitySource,
				entityDescriptor
		);
		entityDescriptor.setTable( primaryTable );

		if ( log.isDebugEnabled() ) {
			log.debugf( "Mapping union-subclass: %s -> %s", entityDescriptor.getEntityName(), primaryTable.getName() );
		}

		// todo : tooling hints

		bindAllEntityAttributes(
				entitySource.sourceMappingDocument(),
				entitySource,
				entityDescriptor
		);

		bindUnionSubclassEntities( entitySource, entityDescriptor );
	}
