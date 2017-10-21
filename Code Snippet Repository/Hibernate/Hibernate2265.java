	public void sortCollectionActions() {
		if ( isOrderUpdatesEnabled() ) {
			// sort the updates by fk
			if( collectionCreations != null ) {
				collectionCreations.sort();
			}
			if( collectionUpdates != null ) {
				collectionUpdates.sort();
			}
			if( collectionQueuedOps != null ) {
				collectionQueuedOps.sort();
			}
			if( collectionRemovals != null ) {
				collectionRemovals.sort();
			}
		}
	}
