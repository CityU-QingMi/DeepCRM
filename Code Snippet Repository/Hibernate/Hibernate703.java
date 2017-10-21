	public MetadataBuilderImpl(MetadataSources sources, StandardServiceRegistry serviceRegistry) {
		this.sources = sources;
		this.options = new MetadataBuildingOptionsImpl( serviceRegistry );

		for ( MetadataSourcesContributor contributor :
				sources.getServiceRegistry()
						.getService( ClassLoaderService.class )
						.loadJavaServices( MetadataSourcesContributor.class ) ) {
			contributor.contribute( sources );
		}

		// todo : not so sure this is needed anymore.
		//		these should be set during the StandardServiceRegistryBuilder.configure call
		applyCfgXmlValues( serviceRegistry.getService( CfgXmlAccessService.class ) );

		final ClassLoaderService classLoaderService = serviceRegistry.getService( ClassLoaderService.class );
		for ( MetadataBuilderInitializer contributor : classLoaderService.loadJavaServices( MetadataBuilderInitializer.class ) ) {
			contributor.contribute( this, serviceRegistry );
		}
	}
