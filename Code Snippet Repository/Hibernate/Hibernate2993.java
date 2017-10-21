	@Override
	public String[] getSecondLevelCacheRegionNames() {
		final Set<String> names = new HashSet<>();
		names.addAll( entityRegionAccessStrategyMap.keySet() );
		names.addAll( collectionRegionAccessStrategyMap.keySet() );
		names.addAll( naturalIdRegionAccessStrategyMap.keySet() );
		if ( settings.isQueryCacheEnabled() ) {
			names.add( updateTimestampsCache.getRegion().getName() );
			names.addAll( queryCaches.keySet() );
		}
		return ArrayHelper.toStringArray( names );
	}
