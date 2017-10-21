	@Override
	@SuppressWarnings("")
	public boolean update(SharedSessionContractImplementor session, Object key, Object value, Object currentVersion, Object previousVersion)
			throws CacheException {
		// We update whether or not the region is valid. Other nodes
		// may have already restored the region so they need to
		// be informed of the change.

		// We need to be invalidating even for regular writes; if we were not and the write was followed by eviction
		// (or any other invalidation), naked put that was started after the eviction ended but before this update
		// ended could insert the stale entry into the cache (since the entry was removed by eviction).

		// The beginInvalidateKey(...) is called from TxPutFromLoadInterceptor because we need the global transaction id.
		writeCache.put(key, value);
		return true;
	}
