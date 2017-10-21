	@Override
	public int getRegionCacheOrphanEvictionPeriod(String region) {
		final Cache cache = this.cacheManager.getCache( region );
		if ( cache != null && cache.isTerracottaClustered() ) {
			return cache.getCacheConfiguration().getTerracottaConfiguration().getOrphanEvictionPeriod();
		}
		else {
			return -1;
		}
	}
