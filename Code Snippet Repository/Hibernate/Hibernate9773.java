	@Override
	public long getMaxGetTimeMillis(String cacheName) {
		final Cache cache = cacheManager.getCache( cacheName );
		if ( cache != null ) {
			final Long maximum = cache.getStatistics()
					.cacheGetOperation()
					.latency()
					.maximum().value();
			return maximum == null ? 0 : TimeUnit.MILLISECONDS.convert(
					maximum,
					TimeUnit.NANOSECONDS
			);
		}
		else {
			return 0;
		}
	}
