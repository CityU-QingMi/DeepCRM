	@SuppressWarnings("")
	public CacheRegionStats(final CompositeData cData) {
		int i = 0;
		region = (String) cData.get( ITEM_NAMES[i++] );
		shortName = (String) cData.get( ITEM_NAMES[i++] );
		hitCount = (Long) cData.get( ITEM_NAMES[i++] );
		missCount = (Long) cData.get( ITEM_NAMES[i++] );
		putCount = (Long) cData.get( ITEM_NAMES[i++] );
		hitRatio = (Double) cData.get( ITEM_NAMES[i++] );
		elementCountInMemory = (Long) cData.get( ITEM_NAMES[i++] );
		elementCountOnDisk = (Long) cData.get( ITEM_NAMES[i++] );
		elementCountTotal = (Long) cData.get( ITEM_NAMES[i++] );
	}
