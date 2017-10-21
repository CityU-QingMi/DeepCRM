	@Before
	public void setup() {
		this.cacheManager = getCachingProvider().getCacheManager();
		this.cacheManager.createCache(CACHE_NAME, new MutableConfiguration<>());
		this.cacheManager.createCache(CACHE_NAME_NO_NULL, new MutableConfiguration<>());
		this.nativeCache = this.cacheManager.getCache(CACHE_NAME);
		this.cache = new JCacheCache(this.nativeCache);
		Cache<Object, Object> nativeCacheNoNull =
				this.cacheManager.getCache(CACHE_NAME_NO_NULL);
		this.cacheNoNull = new JCacheCache(nativeCacheNoNull, false);
	}
