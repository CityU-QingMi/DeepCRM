	@Override
	public TimestampsRegion buildTimestampsRegion(String regionName, Map<String, Object> configValues) {
		if ( log.isDebugEnabled() ) {
			log.debug( "Building timestamps cache region [" + regionName + "]" );
		}
		final AdvancedCache cache = getCache( regionName, DataType.TIMESTAMPS, null);
		final TimestampsRegionImpl region = createTimestampsRegion( cache, regionName );
		startRegion( region );
		return region;
	}
