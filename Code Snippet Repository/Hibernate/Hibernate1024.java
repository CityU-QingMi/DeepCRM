	public Binding bind(File file) {
		final Origin origin = new Origin( SourceType.FILE, file.getPath() );
		LOG.tracef( "reading mappings from file : %s", origin.getName() );

		if ( !file.exists() ) {
			throw new MappingNotFoundException( origin );
		}

		return new FileXmlSource( origin, file ).doBind( getMappingBinder() );
	}
