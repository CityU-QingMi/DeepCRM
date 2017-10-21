	public MetadataBuildingContextRootImpl(
			MetadataBuildingOptions options,
			ClassLoaderAccess classLoaderAccess,
			InFlightMetadataCollector metadataCollector) {
		this.options = options;
		this.mappingDefaults = options.getMappingDefaults();
		this.classLoaderAccess = classLoaderAccess;
		this.metadataCollector = metadataCollector;
		this.objectNameNormalizer = new ObjectNameNormalizer() {
			@Override
			protected MetadataBuildingContext getBuildingContext() {
				return MetadataBuildingContextRootImpl.this;
			}
		};
	}
