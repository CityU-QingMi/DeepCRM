	private void processExportableProducers(MetadataBuildingContext buildingContext) {
		// for now we only handle id generators as ExportableProducers

		final Dialect dialect = getDatabase().getJdbcEnvironment().getDialect();
		final String defaultCatalog = extractName( getDatabase().getDefaultNamespace().getName().getCatalog(), dialect );
		final String defaultSchema = extractName( getDatabase().getDefaultNamespace().getName().getSchema(), dialect );

		for ( PersistentClass entityBinding : entityBindingMap.values() ) {
			if ( entityBinding.isInherited() ) {
				continue;
			}

			handleIdentifierValueBinding(
					entityBinding.getIdentifier(),
					dialect,
					defaultCatalog,
					defaultSchema,
					(RootClass) entityBinding
			);
		}

		for ( Collection collection : collectionBindingMap.values() ) {
			if ( !IdentifierCollection.class.isInstance( collection ) ) {
				continue;
			}

			handleIdentifierValueBinding(
					( (IdentifierCollection) collection ).getIdentifier(),
					dialect,
					defaultCatalog,
					defaultSchema,
					null
			);
		}
	}
