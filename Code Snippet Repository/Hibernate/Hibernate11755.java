	protected CacheManager getCacheManager(final Properties properties){
		final CachingProvider cachingProvider = getCachingProvider( properties );
		final CacheManager cacheManager;
		final String cacheManagerUri = getProp( properties, CONFIG_URI );
		if ( cacheManagerUri != null ) {
			URI uri;
			try {
				uri = new URI( cacheManagerUri );
			}
			catch ( URISyntaxException e ) {
				throw new CacheException( "Couldn't create URI from " + cacheManagerUri, e );
			}
			cacheManager = cachingProvider.getCacheManager( uri, cachingProvider.getDefaultClassLoader() );
		}
		else {
			cacheManager = cachingProvider.getCacheManager();
		}
		return cacheManager;
	}
