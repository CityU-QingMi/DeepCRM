	protected CacheResolver getCacheResolver(CacheInvocationContext<? extends Annotation> context, String cacheName) {
		CacheResolver cacheResolver = mock(CacheResolver.class);
		javax.cache.Cache cache;
		if (cacheName == null) {
			cache = null;
		}
		else {
			cache = mock(javax.cache.Cache.class);
			given(cache.getName()).willReturn(cacheName);
		}
		given(cacheResolver.resolveCache(context)).willReturn(cache);
		return cacheResolver;
	}
