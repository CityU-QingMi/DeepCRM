	@Override
	public float getAverageGetTimeMillis(String region) {
		final Cache cache = this.cacheManager.getCache( region );
		if ( cache != null ) {
			final Double avg = cache.getStatistics()
					.cacheGetOperation()
					.latency()
					.average().value();
			return TimeUnit.MILLISECONDS.convert(
					avg.longValue(),
					TimeUnit.NANOSECONDS
			);
		}
		else {
			return -1f;
		}
	}
