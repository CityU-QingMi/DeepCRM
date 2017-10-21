	/* package-private */ void setOperatedOn(Object mergeEntity, boolean isOperatedOn) {
		if ( mergeEntity == null ) {
			throw new NullPointerException( "null entities are not supported by " + getClass().getName() );
		}
		if ( ! mergeEntityToOperatedOnFlagMap.containsKey( mergeEntity ) ||
			! mergeToManagedEntityXref.containsKey( mergeEntity ) ) {
			throw new IllegalStateException( "called MergeContext#setOperatedOn() for mergeEntity not found in MergeContext" );
		}
		mergeEntityToOperatedOnFlagMap.put( mergeEntity, isOperatedOn );
	}
