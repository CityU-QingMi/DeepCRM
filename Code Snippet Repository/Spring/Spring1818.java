	@Override
	protected JCacheOperation<?> findCacheOperation(Method method, Class<?> targetType) {
		CacheResult cacheResult = method.getAnnotation(CacheResult.class);
		CachePut cachePut = method.getAnnotation(CachePut.class);
		CacheRemove cacheRemove = method.getAnnotation(CacheRemove.class);
		CacheRemoveAll cacheRemoveAll = method.getAnnotation(CacheRemoveAll.class);

		int found = countNonNull(cacheResult, cachePut, cacheRemove, cacheRemoveAll);
		if (found == 0) {
			return null;
		}
		if (found > 1) {
			throw new IllegalStateException("More than one cache annotation found on '" + method + "'");
		}

		CacheDefaults defaults = getCacheDefaults(method, targetType);
		if (cacheResult != null) {
			return createCacheResultOperation(method, defaults, cacheResult);
		}
		else if (cachePut != null) {
			return createCachePutOperation(method, defaults, cachePut);
		}
		else if (cacheRemove != null) {
			return createCacheRemoveOperation(method, defaults, cacheRemove);
		}
		else {
			return createCacheRemoveAllOperation(method, defaults, cacheRemoveAll);
		}
	}
