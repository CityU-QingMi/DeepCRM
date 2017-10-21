	@Override
	public NaturalIdRegion buildNaturalIdRegion(String regionName, Properties properties, CacheDataDescription metadata)
			throws CacheException {
		return new EhcacheNaturalIdRegion(
				accessStrategyFactory,
				getCache( regionName ),
				settings,
				metadata,
				properties
		);
	}
