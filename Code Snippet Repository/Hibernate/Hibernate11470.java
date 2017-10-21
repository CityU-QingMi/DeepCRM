	public static AdvancedCache failSilentWriteCache(
			AdvancedCache cache,
			Flag extraFlag) {
		return cache.withFlags(
				Flag.FAIL_SILENTLY,
				Flag.ZERO_LOCK_ACQUISITION_TIMEOUT,
				Flag.SKIP_CACHE_LOAD,
				Flag.SKIP_REMOTE_LOOKUP,
				extraFlag
		);
	}
