	protected CacheResultOperation createCacheResultOperation(Method method, CacheDefaults defaults, CacheResult ann) {
		String cacheName = determineCacheName(method, defaults, ann.cacheName());
		CacheResolverFactory cacheResolverFactory =
				determineCacheResolverFactory(defaults, ann.cacheResolverFactory());
		KeyGenerator keyGenerator = determineKeyGenerator(defaults, ann.cacheKeyGenerator());

		CacheMethodDetails<CacheResult> methodDetails = createMethodDetails(method, ann, cacheName);

		CacheResolver cacheResolver = getCacheResolver(cacheResolverFactory, methodDetails);
		CacheResolver exceptionCacheResolver = null;
		final String exceptionCacheName = ann.exceptionCacheName();
		if (StringUtils.hasText(exceptionCacheName)) {
			exceptionCacheResolver = getExceptionCacheResolver(cacheResolverFactory, methodDetails);
		}

		return new CacheResultOperation(methodDetails, cacheResolver, keyGenerator, exceptionCacheResolver);
	}
