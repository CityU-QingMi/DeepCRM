	@Override
	public EntityRegion buildEntityRegion(String regionName, Map<String, Object> configValues, CacheDataDescription metadata) {
		if ( log.isDebugEnabled() ) {
			log.debugf(
					"Building entity cache region [%s] (mutable=%s, versioned=%s)",
					regionName,
					metadata.isMutable(),
					metadata.isVersioned()
			);
		}
		final AdvancedCache cache = getCache( regionName, metadata.isMutable() ? DataType.ENTITY : DataType.IMMUTABLE_ENTITY, metadata );
		final EntityRegionImpl region = new EntityRegionImpl( cache, regionName, transactionManager, metadata, this, getCacheKeysFactory() );
		startRegion( region );
		return region;
	}
