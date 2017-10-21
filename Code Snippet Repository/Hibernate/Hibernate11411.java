	@Override
	public boolean putFromLoad(SharedSessionContractImplementor session, Object key, Object value, long txTimestamp, Object version, boolean minimalPutOverride) throws CacheException {
		long lastRegionInvalidation = region.getLastRegionInvalidation();
		if (txTimestamp < lastRegionInvalidation) {
			log.tracef("putFromLoad not executed since tx started at %d, before last region invalidation finished = %d", txTimestamp, lastRegionInvalidation);
			return false;
		}
		if (minimalPutOverride) {
			Object prev = cache.get(key);
			if (prev instanceof Tombstone) {
				Tombstone tombstone = (Tombstone) prev;
				long lastTimestamp = tombstone.getLastTimestamp();
				if (txTimestamp <= lastTimestamp) {
					log.tracef("putFromLoad not executed since tx started at %d, before last invalidation finished = %d", txTimestamp, lastTimestamp);
					return false;
				}
			}
			else if (prev != null) {
				log.tracef("putFromLoad not executed since cache contains %s", prev);
				return false;
			}
		}
		// we can't use putForExternalRead since the PFER flag means that entry is not wrapped into context
		// when it is present in the container. TombstoneCallInterceptor will deal with this.
		putFromLoadCache.put(key, new TombstoneUpdate(session.getTimestamp(), value));
		return true;
	}
