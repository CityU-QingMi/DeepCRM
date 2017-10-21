	@Override
	public TabularData getCacheRegionStats() {
		final List<CompositeData> list = new ArrayList<CompositeData>();
		final Statistics statistics = getStatistics();
		for ( String region : statistics.getSecondLevelCacheRegionNames() ) {
			final CacheRegionStats l2CacheStats = new CacheRegionStats(
					region,
					statistics.getSecondLevelCacheStatistics( region )
			);
			list.add( l2CacheStats.toCompositeData() );
		}
		final TabularData td = CacheRegionStats.newTabularDataInstance();
		td.putAll( list.toArray( new CompositeData[list.size()] ) );
		return td;
	}
