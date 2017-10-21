	protected void createSchemaAndCatalog(
			DatabaseInformation existingDatabase,
			ExecutionOptions options,
			Dialect dialect,
			Formatter formatter,
			boolean tryToCreateCatalogs,
			boolean tryToCreateSchemas,
			Set<Identifier> exportedCatalogs, Namespace namespace, GenerationTarget[] targets) {
		if ( tryToCreateCatalogs || tryToCreateSchemas ) {
			if ( tryToCreateCatalogs ) {
				final Identifier catalogLogicalName = namespace.getName().getCatalog();
				final Identifier catalogPhysicalName = namespace.getPhysicalName().getCatalog();

				if ( catalogPhysicalName != null && !exportedCatalogs.contains( catalogLogicalName )
						&& !existingDatabase.catalogExists( catalogLogicalName ) ) {
					applySqlStrings(
							false,
							dialect.getCreateCatalogCommand( catalogPhysicalName.render( dialect ) ),
							formatter,
							options,
							targets
					);
					exportedCatalogs.add( catalogLogicalName );
				}
			}

			if ( tryToCreateSchemas
					&& namespace.getPhysicalName().getSchema() != null
					&& !existingDatabase.schemaExists( namespace.getName() ) ) {
				applySqlStrings(
						false,
						dialect.getCreateSchemaCommand( namespace.getPhysicalName().getSchema().render( dialect ) ),
						formatter,
						options,
						targets
				);
			}
		}
	}
