	protected CachingProvider getCachingProvider(final Properties properties){
		final CachingProvider cachingProvider;
		final String provider = getProp( properties, PROVIDER );
		if ( provider != null ) {
			cachingProvider = Caching.getCachingProvider( provider );
		}
		else {
			cachingProvider = Caching.getCachingProvider();
		}
		return cachingProvider;
	}
