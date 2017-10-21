	private Map<String,ParsedPersistenceXmlDescriptor> doResolve(Map integration) {
		final Map<String,ParsedPersistenceXmlDescriptor> persistenceUnits = new ConcurrentHashMap<>();

		final List<URL> xmlUrls = classLoaderService.locateResources( "META-INF/persistence.xml" );
		if ( xmlUrls.isEmpty() ) {
			LOG.unableToFindPersistenceXmlInClasspath();
		}
		else {
			for ( URL xmlUrl : xmlUrls ) {
				persistenceUnits.putAll( parsePersistenceXml( xmlUrl, integration ) );
			}
		}

		return persistenceUnits;
	}
