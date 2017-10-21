	@Nullable
	protected CacheResolverFactory determineCacheResolverFactory(CacheDefaults defaults,
			Class<? extends CacheResolverFactory> candidate) {

		if (CacheResolverFactory.class != candidate) {
			return getBean(candidate);
		}
		else if (defaults != null && CacheResolverFactory.class != defaults.cacheResolverFactory()) {
			return getBean(defaults.cacheResolverFactory());
		}
		else {
			return null;
		}
	}
