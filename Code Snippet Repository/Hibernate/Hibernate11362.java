	@Override
	public QueryResultsRegion buildQueryResultsRegion(String regionName, Map<String, Object> configValues) {
		if ( log.isDebugEnabled() ) {
			log.debug( "Building query results cache region [" + regionName + "]" );
		}

		final AdvancedCache cache = getCache( regionName, DataType.QUERY, null);
		final QueryResultsRegionImpl region = new QueryResultsRegionImpl( cache, regionName, transactionManager, this );
		startRegion( region );
		return region;
	}
