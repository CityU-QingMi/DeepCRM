	public void clearFromFlushNeededCheck(int previousCollectionRemovalSize) {
		if( collectionCreations != null ) {
			collectionCreations.clear();
		}
		if( collectionUpdates != null ) {
			collectionUpdates.clear();
		}
		if( collectionQueuedOps != null ) {
			collectionQueuedOps.clear();
		}
		if( updates != null) {
			updates.clear();
		}
		// collection deletions are a special case since update() can add
		// deletions of collections not loaded by the session.
		if ( collectionRemovals != null && collectionRemovals.size() > previousCollectionRemovalSize ) {
			collectionRemovals.removeLastN( collectionRemovals.size() - previousCollectionRemovalSize );
		}
	}
