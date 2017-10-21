	@Override
	public void prepare() {
		// use any persistence-unit-defaults defined in orm.xml
		( (JpaOrmXmlPersistenceUnitDefaultAware) rootMetadataBuildingContext.getBuildingOptions() ).apply(
				new JpaOrmXmlPersistenceUnitDefaults() {
					final Map persistenceUnitDefaults = reflectionManager.getDefaults();

					@Override
					public String getDefaultSchemaName() {
						return StringHelper.nullIfEmpty( (String) persistenceUnitDefaults.get( "schema" ) );
					}

					@Override
					public String getDefaultCatalogName() {
						return StringHelper.nullIfEmpty( (String) persistenceUnitDefaults.get( "catalog" ) );
					}

					@Override
					public boolean shouldImplicitlyQuoteIdentifiers() {
						final Object isDelimited = persistenceUnitDefaults.get( "delimited-identifier" );
						return isDelimited != null && isDelimited == Boolean.TRUE;
					}
				}
		);

		rootMetadataBuildingContext.getMetadataCollector().getDatabase().adjustDefaultNamespace(
				rootMetadataBuildingContext.getBuildingOptions().getMappingDefaults().getImplicitCatalogName(),
				rootMetadataBuildingContext.getBuildingOptions().getMappingDefaults().getImplicitSchemaName()
		);

		AnnotationBinder.bindDefaults( rootMetadataBuildingContext );
		for ( String annotatedPackage : annotatedPackages ) {
			AnnotationBinder.bindPackage( annotatedPackage, rootMetadataBuildingContext );
		}
	}
