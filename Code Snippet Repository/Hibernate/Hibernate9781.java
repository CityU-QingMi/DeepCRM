	@Override
	public int getRegionCacheMaxTTISeconds(String region) {
		final Cache cache = cacheManager.getCache( region );
		if ( cache != null ) {
			return (int) cache.getCacheConfiguration().getTimeToIdleSeconds();
		}
		else {
			return -1;
		}
	}
