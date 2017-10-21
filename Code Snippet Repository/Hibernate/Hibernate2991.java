	@Override
	public void close() {
		for ( EntityRegionAccessStrategy access : entityRegionAccessStrategyMap.values() ) {
			access.getRegion().destroy();
		}

		for ( CollectionRegionAccessStrategy access : collectionRegionAccessStrategyMap.values() ) {
			access.getRegion().destroy();
		}

		if ( settings.isQueryCacheEnabled() ) {
			defaultQueryCache.destroy();

			for ( QueryCache cache : queryCaches.values() ) {
				cache.destroy();
			}
			updateTimestampsCache.destroy();
		}

		regionFactory.stop();
	}
