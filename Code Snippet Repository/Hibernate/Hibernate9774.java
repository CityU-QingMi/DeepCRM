	@Override
	public long getMinGetTimeMillis(String cacheName) {
		final Cache cache = cacheManager.getCache( cacheName );
		if ( cache != null ) {
			final Long minimum = cache.getStatistics()
					.cacheGetOperation()
					.latency()
					.minimum().value();
			return minimum == null ? 0 : TimeUnit.MILLISECONDS.convert(
					minimum,
					TimeUnit.NANOSECONDS
			);
		}
		else {
			return 0;
		}
	}
