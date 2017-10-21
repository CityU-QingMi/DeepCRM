	public static AdvancedCache asyncWriteCache(
			AdvancedCache cache,
			Flag extraFlag) {
		return cache.withFlags(
				Flag.SKIP_CACHE_LOAD,
				Flag.SKIP_REMOTE_LOOKUP,
				Flag.FORCE_ASYNCHRONOUS,
				extraFlag
		);
	}
