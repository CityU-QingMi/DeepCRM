	private static MetadataImplementor buildMetadata(CommandLineArgs parsedArgs, ServiceRegistry serviceRegistry)
			throws Exception {
		final MetadataSources metadataSources = new MetadataSources( serviceRegistry );

		for ( String filename : parsedArgs.hbmXmlFiles ) {
			metadataSources.addFile( filename );
		}

		for ( String filename : parsedArgs.jarFiles ) {
			metadataSources.addJar( new File( filename ) );
		}


		final MetadataBuilder metadataBuilder = metadataSources.getMetadataBuilder();
		final StrategySelector strategySelector = serviceRegistry.getService( StrategySelector.class );
		if ( parsedArgs.implicitNamingStrategyImplName != null ) {
			metadataBuilder.applyImplicitNamingStrategy(
					strategySelector.resolveStrategy(
							ImplicitNamingStrategy.class,
							parsedArgs.implicitNamingStrategyImplName
					)
			);
		}
		if ( parsedArgs.physicalNamingStrategyImplName != null ) {
			metadataBuilder.applyPhysicalNamingStrategy(
					strategySelector.resolveStrategy(
							PhysicalNamingStrategy.class,
							parsedArgs.physicalNamingStrategyImplName
					)
			);
		}

		return (MetadataImplementor) metadataBuilder.build();
	}
