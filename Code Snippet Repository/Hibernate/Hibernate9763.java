	@Override
	public int getRegionCacheTargetMaxTotalCount(String region) {
		final Cache cache = cacheManager.getCache( region );
		if ( cache != null ) {
			return cache.getCacheConfiguration().getMaxElementsOnDisk();
		}
		else {
			return -1;
		}
	}
