	@SuppressWarnings("")
	public CollectionStats(final CompositeData cData) {
		int i = 0;
		roleName = (String) cData.get( ITEM_NAMES[i++] );
		shortName = (String) cData.get( ITEM_NAMES[i++] );
		loadCount = (Long) cData.get( ITEM_NAMES[i++] );
		fetchCount = (Long) cData.get( ITEM_NAMES[i++] );
		updateCount = (Long) cData.get( ITEM_NAMES[i++] );
		removeCount = (Long) cData.get( ITEM_NAMES[i++] );
		recreateCount = (Long) cData.get( ITEM_NAMES[i++] );
	}
