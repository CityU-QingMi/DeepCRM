	public MetadataImpl buildMetadataInstance(MetadataBuildingContext buildingContext) {
		processSecondPasses( buildingContext );
		processExportableProducers( buildingContext );

		try {
			return new MetadataImpl(
					uuid,
					options,
					typeResolver,
					identifierGeneratorFactory,
					entityBindingMap,
					mappedSuperClasses,
					collectionBindingMap,
					typeDefinitionMap,
					filterDefinitionMap,
					fetchProfileMap,
					imports,
					idGeneratorDefinitionMap,
					namedQueryMap,
					namedNativeQueryMap,
					namedProcedureCallMap,
					sqlResultSetMappingMap,
					namedEntityGraphMap,
					sqlFunctionMap,
					getDatabase()
			);
		}
		finally {
			classmateContext.release();
		}
	}
