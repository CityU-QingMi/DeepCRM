	@Nullable
	private Collection<CacheOperation> parseCacheAnnotations(
			DefaultCacheConfig cachingConfig, AnnotatedElement ae, boolean localOnly) {

		Collection<CacheOperation> ops = null;

		Collection<Cacheable> cacheables = (localOnly ? AnnotatedElementUtils.getAllMergedAnnotations(ae, Cacheable.class) :
				AnnotatedElementUtils.findAllMergedAnnotations(ae, Cacheable.class));
		if (!cacheables.isEmpty()) {
			ops = lazyInit(null);
			for (Cacheable cacheable : cacheables) {
				ops.add(parseCacheableAnnotation(ae, cachingConfig, cacheable));
			}
		}
		Collection<CacheEvict> evicts = (localOnly ? AnnotatedElementUtils.getAllMergedAnnotations(ae, CacheEvict.class) :
				AnnotatedElementUtils.findAllMergedAnnotations(ae, CacheEvict.class));
		if (!evicts.isEmpty()) {
			ops = lazyInit(ops);
			for (CacheEvict evict : evicts) {
				ops.add(parseEvictAnnotation(ae, cachingConfig, evict));
			}
		}
		Collection<CachePut> puts = (localOnly ? AnnotatedElementUtils.getAllMergedAnnotations(ae, CachePut.class) :
				AnnotatedElementUtils.findAllMergedAnnotations(ae, CachePut.class));
		if (!puts.isEmpty()) {
			ops = lazyInit(ops);
			for (CachePut put : puts) {
				ops.add(parsePutAnnotation(ae, cachingConfig, put));
			}
		}
		Collection<Caching> cachings = (localOnly ? AnnotatedElementUtils.getAllMergedAnnotations(ae, Caching.class) :
				AnnotatedElementUtils.findAllMergedAnnotations(ae, Caching.class));
		if (!cachings.isEmpty()) {
			ops = lazyInit(ops);
			for (Caching caching : cachings) {
				Collection<CacheOperation> cachingOps = parseCachingAnnotation(ae, cachingConfig, caching);
				if (cachingOps != null) {
					ops.addAll(cachingOps);
				}
			}
		}

		return ops;
	}
