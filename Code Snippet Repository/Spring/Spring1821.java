	protected CacheRemoveOperation createCacheRemoveOperation(Method method, CacheDefaults defaults, CacheRemove ann) {
		String cacheName = determineCacheName(method, defaults, ann.cacheName());
		CacheResolverFactory cacheResolverFactory =
				determineCacheResolverFactory(defaults, ann.cacheResolverFactory());
		KeyGenerator keyGenerator = determineKeyGenerator(defaults, ann.cacheKeyGenerator());

		CacheMethodDetails<CacheRemove> methodDetails = createMethodDetails(method, ann, cacheName);
		CacheResolver cacheResolver = getCacheResolver(cacheResolverFactory, methodDetails);
		return new CacheRemoveOperation(methodDetails, cacheResolver, keyGenerator);
	}
