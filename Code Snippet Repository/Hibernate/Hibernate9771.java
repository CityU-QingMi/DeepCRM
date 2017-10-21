	@Override
	public long getMaxGetTimeMillis() {
		long rv = 0;
		for ( String cacheName : cacheManager.getCacheNames() ) {
			final Cache cache = cacheManager.getCache( cacheName );
			if ( cache != null ) {
				final Long maximum = cache.getStatistics()
						.cacheGetOperation().latency().maximum().value();
				if ( maximum != null ) {
					rv = Math.max(
							rv, TimeUnit.MILLISECONDS.convert(
							maximum,
							TimeUnit.NANOSECONDS
					)
					);
				}
			}
		}
		return rv;
	}
