	@Override
	public Collection<? extends Cache> resolveCaches(CacheOperationInvocationContext<?> context) {
		if (!(context instanceof CacheInvocationContext<?>)) {
			throw new IllegalStateException("Unexpected context " + context);
		}
		CacheInvocationContext<?> cacheInvocationContext = (CacheInvocationContext<?>) context;
		javax.cache.Cache<Object, Object> cache = target.resolveCache(cacheInvocationContext);
		Assert.notNull(cache, "Cannot resolve cache for '" + context + "' using '" + target + "'");
		return Collections.singleton(new JCacheCache(cache));
	}
