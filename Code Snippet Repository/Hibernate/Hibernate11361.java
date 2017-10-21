	@Override
	public NaturalIdRegion buildNaturalIdRegion(String regionName, Map<String, Object> configValues, CacheDataDescription metadata) {
		if ( log.isDebugEnabled() ) {
			log.debug("Building natural id cache region [" + regionName + "]");
		}
		final AdvancedCache cache = getCache( regionName, DataType.NATURAL_ID, metadata);
		final NaturalIdRegionImpl region = new NaturalIdRegionImpl( cache, regionName, transactionManager, metadata, this, getCacheKeysFactory());
		startRegion( region );
		return region;
	}
