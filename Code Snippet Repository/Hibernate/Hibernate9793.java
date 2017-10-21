	@SuppressWarnings("")
	public QueryStats(final CompositeData cData) {
		int i = 0;
		query = (String) cData.get( ITEM_NAMES[i++] );
		cacheHitCount = (Long) cData.get( ITEM_NAMES[i++] );
		cacheMissCount = (Long) cData.get( ITEM_NAMES[i++] );
		cachePutCount = (Long) cData.get( ITEM_NAMES[i++] );
		executionCount = (Long) cData.get( ITEM_NAMES[i++] );
		executionRowCount = (Long) cData.get( ITEM_NAMES[i++] );
		executionAvgTime = (Long) cData.get( ITEM_NAMES[i++] );
		executionMaxTime = (Long) cData.get( ITEM_NAMES[i++] );
		executionMinTime = (Long) cData.get( ITEM_NAMES[i++] );
	}
