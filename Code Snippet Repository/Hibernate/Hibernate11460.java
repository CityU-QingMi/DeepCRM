	public TimestampsRegionImpl(
			AdvancedCache cache, String name,
			InfinispanRegionFactory factory) {
		super( cache, name, factory );
		this.removeCache = Caches.ignoreReturnValuesCache( cache );

		// Skip locking when updating timestamps to provide better performance
		// under highly concurrent insert scenarios, where update timestamps
		// for an entity/collection type are constantly updated, creating
		// contention.
		//
		// The worst it can happen is that an earlier an earlier timestamp
		// (i.e. ts=1) will override a later on (i.e. ts=2), so it means that
		// in highly concurrent environments, queries might be considered stale
		// earlier in time. The upside is that inserts/updates are way faster
		// in local set ups.
		this.timestampsPutCache = getTimestampsPutCache( cache );
	}
