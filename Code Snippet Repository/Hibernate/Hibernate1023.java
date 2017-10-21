	public Binding bind(String resource) {
		LOG.tracef( "reading mappings from resource : %s", resource );

		final Origin origin = new Origin( SourceType.RESOURCE, resource );
		final URL url = classLoaderService.locateResource( resource );
		if ( url == null ) {
			throw new MappingNotFoundException( origin );
		}

		return new UrlXmlSource( origin, url ).doBind( getMappingBinder() );
	}
