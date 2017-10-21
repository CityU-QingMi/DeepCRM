	@Override
	public long getCacheHitCount() {
		long count = 0;
		for ( String name : cacheManager.getCacheNames() ) {
			final Cache cache = cacheManager.getCache( name );
			if ( cache != null ) {
				count += cache.getStatistics().cacheHitCount();
			}
		}
		return count;
	}
