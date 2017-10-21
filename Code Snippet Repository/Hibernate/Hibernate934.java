	private void bindDiscriminatorSubclassEntity(
			SubclassEntitySourceImpl entitySource,
			SingleTableSubclass entityDescriptor) {

		bindBasicEntityValues(
				entitySource.sourceMappingDocument(),
				entitySource,
				entityDescriptor
		);

		final String superEntityName = ( (EntitySource) entitySource.getSuperType() ).getEntityNamingSource()
				.getEntityName();
		final EntityTableXref superEntityTableXref = entitySource.getLocalMetadataBuildingContext()
				.getMetadataCollector()
				.getEntityTableXref( superEntityName );
		if ( superEntityTableXref == null ) {
			throw new MappingException(
					String.format(
							Locale.ENGLISH,
							"Unable to locate entity table xref for entity [%s] super-type [%s]",
							entityDescriptor.getEntityName(),
							superEntityName
					),
					entitySource.origin()
			);
		}

		entitySource.getLocalMetadataBuildingContext().getMetadataCollector().addEntityTableXref(
				entitySource.getEntityNamingSource().getEntityName(),
				database.toIdentifier(
						entitySource.getLocalMetadataBuildingContext().getMetadataCollector().getLogicalTableName(
								entityDescriptor.getTable()
						)
				),
				entityDescriptor.getTable(),
				superEntityTableXref
		);

		bindAllEntityAttributes(
				entitySource.sourceMappingDocument(),
				entitySource,
				entityDescriptor
		);

		bindDiscriminatorSubclassEntities( entitySource, entityDescriptor );
	}
