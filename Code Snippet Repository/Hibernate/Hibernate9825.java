	@Override
	public void initialize(final MetadataImplementor metadata, final MappingCollector mappingCollector) {
		if ( initialized ) {
			throw new UnsupportedOperationException( "EnversService#initialize should be called only once" );
		}

		initialized = true;


		this.serviceRegistry = metadata.getMetadataBuildingOptions().getServiceRegistry();
		this.classLoaderService = serviceRegistry.getService( ClassLoaderService.class );
		this.xmlHelper = new XMLHelper( classLoaderService );

		doInitialize( metadata, mappingCollector, serviceRegistry );
	}
