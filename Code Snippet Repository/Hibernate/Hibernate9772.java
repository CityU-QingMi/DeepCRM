	@Override
	public long getMinGetTimeMillis() {
		long rv = Long.MAX_VALUE;
		for ( String cacheName : cacheManager.getCacheNames() ) {
			final Cache cache = cacheManager.getCache( cacheName );
			if ( cache != null ) {
				final Long minimum = cache.getStatistics()
						.cacheGetOperation()
						.latency()
						.minimum().value();
				if ( minimum != null ) {
					rv = Math.min(
							rv, TimeUnit.MILLISECONDS.convert(
							minimum,
							TimeUnit.NANOSECONDS
					)
					);
				}
			}
		}
		return rv == Long.MAX_VALUE ? 0 : rv;
	}
