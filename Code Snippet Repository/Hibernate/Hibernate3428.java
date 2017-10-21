	public static CacheStoreMode interpretCacheStoreMode(CacheMode cacheMode) {
		if ( cacheMode == null ) {
			cacheMode = DEFAULT_LEGACY_MODE;
		}

		if ( CacheMode.REFRESH == cacheMode ) {
			return CacheStoreMode.REFRESH;
		}
		if ( CacheMode.NORMAL == cacheMode || CacheMode.PUT == cacheMode ) {
			return CacheStoreMode.USE;
		}
		return CacheStoreMode.BYPASS;
	}
