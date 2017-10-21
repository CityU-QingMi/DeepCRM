	@Override
	public boolean putFromLoad(SharedSessionContractImplementor session, Object key, Object value, long txTimestamp, Object version, boolean minimalPutOverride) throws CacheException {
		long lastRegionInvalidation = region.getLastRegionInvalidation();
		if (txTimestamp < lastRegionInvalidation) {
			log.tracef("putFromLoad not executed since tx started at %d, before last region invalidation finished = %d", txTimestamp, lastRegionInvalidation);
			return false;
		}
		assert version != null;

		if (minimalPutOverride) {
			Object prev = cache.get(key);
			if (prev != null) {
				Object oldVersion = getVersion(prev);
				if (oldVersion != null) {
					if (versionComparator.compare(version, oldVersion) <= 0) {
						if (trace) {
							log.tracef("putFromLoad not executed since version(%s) <= oldVersion(%s)", version, oldVersion);
						}
						return false;
					}
				}
				else if (prev instanceof VersionedEntry && txTimestamp <= ((VersionedEntry) prev).getTimestamp()) {
					if (trace) {
						log.tracef("putFromLoad not executed since tx started at %d and entry was invalidated at %d",
								txTimestamp, ((VersionedEntry) prev).getTimestamp());
					}
					return false;
				}
			}
		}
		// we can't use putForExternalRead since the PFER flag means that entry is not wrapped into context
		// when it is present in the container. TombstoneCallInterceptor will deal with this.
		// Even if value is instanceof CacheEntry, we have to wrap it in VersionedEntry and add transaction timestamp.
		// Otherwise, old eviction record wouldn't be overwritten.
		putFromLoadCache.put(key, new VersionedEntry(value, version, txTimestamp));
		return true;
	}
