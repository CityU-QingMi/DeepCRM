	@Override
	public CollectionRegion buildCollectionRegion(String regionName, Map<String, Object> configValues, CacheDataDescription metadata) {
		if ( log.isDebugEnabled() ) {
			log.debug( "Building collection cache region [" + regionName + "]" );
		}
		final AdvancedCache cache = getCache( regionName, DataType.COLLECTION, metadata);
		final CollectionRegionImpl region = new CollectionRegionImpl( cache, regionName, transactionManager, metadata, this, getCacheKeysFactory() );
		startRegion( region );
		return region;
	}
