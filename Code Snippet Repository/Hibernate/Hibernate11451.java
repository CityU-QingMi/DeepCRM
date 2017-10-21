	@Override
	public Map toMap() {
		if (strategy == null) {
			throw new IllegalStateException("Strategy was not set");
		}
		switch (strategy) {
			case NONE:
			case VALIDATION:
				return super.toMap();
			case TOMBSTONES:
				return Caches.entrySet(Caches.localCache(cache), Tombstone.EXCLUDE_TOMBSTONES).toMap();
			case VERSIONED_ENTRIES:
				return Caches.entrySet(Caches.localCache(cache), VersionedEntry.EXCLUDE_EMPTY_EXTRACT_VALUE, VersionedEntry.EXCLUDE_EMPTY_EXTRACT_VALUE).toMap();
			default:
				throw new IllegalStateException(strategy.toString());
		}
	}
