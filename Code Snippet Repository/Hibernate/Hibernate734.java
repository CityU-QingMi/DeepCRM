	public CacheableFileXmlSource(Origin origin, File xmlFile, boolean strict) {
		super( origin );
		this.xmlFile = xmlFile;
		this.strict = strict;

		this.serFile = determineCachedFile( xmlFile );

		if ( strict ) {
			if ( !serFile.exists() ) {
				throw new MappingException(
						String.format( "Cached file [%s] could not be found", origin.getName() ),
						origin
				);
			}
			if ( isSerfileObsolete() ) {
				throw new MappingException(
						String.format( "Cached file [%s] could not be used as the mapping file is newer", origin.getName() ),
						origin
				);
			}
		}
	}
