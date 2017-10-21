	public MetadataBuildingContextTestingImpl(StandardServiceRegistry serviceRegistry) {
		buildingOptions = new MetadataBuilderImpl.MetadataBuildingOptionsImpl( serviceRegistry );
		mappingDefaults = new MetadataBuilderImpl.MappingDefaultsImpl( serviceRegistry );
		metadataCollector = new InFlightMetadataCollectorImpl( buildingOptions, new TypeResolver() );
		classLoaderAccess = new ClassLoaderAccessImpl( null, serviceRegistry );

		objectNameNormalizer = new ObjectNameNormalizer() {
			@Override
			protected MetadataBuildingContext getBuildingContext() {
				return MetadataBuildingContextTestingImpl.this;
			}
		};
	}
