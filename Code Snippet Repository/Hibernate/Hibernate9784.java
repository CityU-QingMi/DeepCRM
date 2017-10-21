	@SuppressWarnings("")
	public EntityStats(final CompositeData cData) {
		int i = 0;
		name = (String) cData.get( ITEM_NAMES[i++] );
		shortName = (String) cData.get( ITEM_NAMES[i++] );
		loadCount = (Long) cData.get( ITEM_NAMES[i++] );
		updateCount = (Long) cData.get( ITEM_NAMES[i++] );
		insertCount = (Long) cData.get( ITEM_NAMES[i++] );
		deleteCount = (Long) cData.get( ITEM_NAMES[i++] );
		fetchCount = (Long) cData.get( ITEM_NAMES[i++] );
		optimisticFailureCount = (Long) cData.get( ITEM_NAMES[i++] );
	}
