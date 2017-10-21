	protected CacheResolver getExceptionCacheResolver(CacheResolverFactory factory,
			CacheMethodDetails<CacheResult> details) {

		if (factory != null) {
			javax.cache.annotation.CacheResolver cacheResolver = factory.getExceptionCacheResolver(details);
			return new CacheResolverAdapter(cacheResolver);
		}
		else {
			return getDefaultExceptionCacheResolver();
		}
	}
