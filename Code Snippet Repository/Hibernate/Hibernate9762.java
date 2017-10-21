	@Override
	public int getRegionCacheTargetMaxInMemoryCount(String region) {
		final Cache cache = cacheManager.getCache( region );
		if ( cache != null ) {
			return cache.getCacheConfiguration().getMaxElementsInMemory();
		}
		else {
			return -1;
		}
	}
