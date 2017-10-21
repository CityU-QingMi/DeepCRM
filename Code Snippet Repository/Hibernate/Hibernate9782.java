	@Override
	public int getRegionCacheMaxTTLSeconds(String region) {
		final Cache cache = cacheManager.getCache( region );
		if ( cache != null ) {
			return (int) cache.getCacheConfiguration().getTimeToLiveSeconds();
		}
		else {
			return -1;
		}
	}
