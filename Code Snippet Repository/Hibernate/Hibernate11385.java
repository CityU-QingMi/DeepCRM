	@Override
	@SuppressWarnings("")
	public boolean insert(SharedSessionContractImplementor session, Object key, Object value, Object version) throws CacheException {
		if ( !region.checkValid() ) {
			return false;
		}

		// We need to be invalidating even for regular writes; if we were not and the write was followed by eviction
		// (or any other invalidation), naked put that was started after the eviction ended but before this insert
		// ended could insert the stale entry into the cache (since the entry was removed by eviction).
		putValidator.setCurrentSession(session);
		try {
			// NonTxInvalidationInterceptor will call beginInvalidatingWithPFER and change this to a removal because
			// we must publish the new value only after invalidation ends.
			writeCache.put(key, value);
		}
		finally {
			putValidator.resetCurrentSession();
		}
		return true;
	}
