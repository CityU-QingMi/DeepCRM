	@Nullable
	Collection<CacheOperation> parseCachingAnnotation(AnnotatedElement ae, DefaultCacheConfig defaultConfig, Caching caching) {
		Collection<CacheOperation> ops = null;

		Cacheable[] cacheables = caching.cacheable();
		if (!ObjectUtils.isEmpty(cacheables)) {
			ops = lazyInit(null);
			for (Cacheable cacheable : cacheables) {
				ops.add(parseCacheableAnnotation(ae, defaultConfig, cacheable));
			}
		}
		CacheEvict[] cacheEvicts = caching.evict();
		if (!ObjectUtils.isEmpty(cacheEvicts)) {
			ops = lazyInit(ops);
			for (CacheEvict cacheEvict : cacheEvicts) {
				ops.add(parseEvictAnnotation(ae, defaultConfig, cacheEvict));
			}
		}
		CachePut[] cachePuts = caching.put();
		if (!ObjectUtils.isEmpty(cachePuts)) {
			ops = lazyInit(ops);
			for (CachePut cachePut : cachePuts) {
				ops.add(parsePutAnnotation(ae, defaultConfig, cachePut));
			}
		}

		return ops;
	}
