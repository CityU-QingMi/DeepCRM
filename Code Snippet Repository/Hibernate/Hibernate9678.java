	@Override
	public CollectionRegion buildCollectionRegion(
			String regionName,
			Properties properties,
			CacheDataDescription metadata)
			throws CacheException {
		return new EhcacheCollectionRegion(
				accessStrategyFactory,
				getCache( regionName ),
				settings,
				metadata,
				properties
		);
	}
