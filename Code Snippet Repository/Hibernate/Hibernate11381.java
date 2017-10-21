	public NonStrictAccessDelegate(BaseTransactionalDataRegion region) {
		this.region = region;
		this.cache = region.getCache();
		this.writeCache = Caches.ignoreReturnValuesCache(cache);
		// Note that correct behaviour of local and async writes depends on LockingInterceptor (see there for details)
		this.putFromLoadCache = writeCache.withFlags( Flag.ZERO_LOCK_ACQUISITION_TIMEOUT, Flag.FAIL_SILENTLY, Flag.FORCE_ASYNCHRONOUS );
		Configuration configuration = cache.getCacheConfiguration();
		if (configuration.clustering().cacheMode().isInvalidation()) {
			throw new IllegalArgumentException("Nonstrict-read-write mode cannot use invalidation.");
		}
		if (configuration.transaction().transactionMode().isTransactional()) {
			throw new IllegalArgumentException("Currently transactional caches are not supported.");
		}
		this.versionComparator = region.getCacheDataDescription().getVersionComparator();
		if (versionComparator == null) {
			throw new IllegalArgumentException("This strategy requires versioned entities/collections but region " + region.getName() + " contains non-versioned data!");
		}
	}
