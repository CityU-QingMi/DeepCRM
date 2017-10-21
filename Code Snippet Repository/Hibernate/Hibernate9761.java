	@Override
	public Map<String, int[]> getRegionCacheSamples() {
		final Map<String, int[]> rv = new HashMap<String, int[]>();
		for ( String name : cacheManager.getCacheNames() ) {
			final Cache cache = cacheManager.getCache( name );
			if ( cache != null ) {
				final Double hits = cache.getStatistics().cacheHitOperation().rate().value();
				final Double misses = cache.getStatistics().cacheMissNotFoundOperation().rate().value();
				final Double expired = cache.getStatistics().cacheMissExpiredOperation().rate().value();
				final Double puts = cache.getStatistics().cachePutOperation().rate().value();
				rv.put(
						name, new int[] {
						hits.intValue(),
						misses.intValue(),
						expired.intValue(),
						puts.intValue()
				}
				);
			}
		}
		return rv;
	}
