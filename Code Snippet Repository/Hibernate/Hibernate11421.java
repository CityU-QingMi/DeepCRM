	@Override
	@SuppressWarnings("")
	public boolean insert(SharedSessionContractImplementor session, Object key, Object value, Object version) throws CacheException {
		if ( !region.checkValid() ) {
			return false;
		}

		// We need to be invalidating even for regular writes; if we were not and the write was followed by eviction
		// (or any other invalidation), naked put that was started after the eviction ended but before this insert
		// ended could insert the stale entry into the cache (since the entry was removed by eviction).

		// The beginInvalidateKey(...) is called from TxPutFromLoadInterceptor because we need the global transaction id.
		writeCache.put(key, value);
		return true;
	}
