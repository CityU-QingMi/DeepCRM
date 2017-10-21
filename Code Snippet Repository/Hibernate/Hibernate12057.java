	protected void applyMetadataSources(MetadataSources metadataSources) {
		for ( String mapping : getMappings() ) {
			metadataSources.addResource( getBaseForMappings() + mapping );
		}

		for ( Class annotatedClass : getAnnotatedClasses() ) {
			metadataSources.addAnnotatedClass( annotatedClass );
		}

		for ( String annotatedPackage : getAnnotatedPackages() ) {
			metadataSources.addPackage( annotatedPackage );
		}

		for ( String ormXmlFile : getXmlFiles() ) {
			metadataSources.addInputStream( Thread.currentThread().getContextClassLoader().getResourceAsStream( ormXmlFile ) );
		}
	}
