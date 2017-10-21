	@Override
	public Object removeEntity(EntityKey key) {
		final Object entity = entitiesByKey.remove( key );
		final Iterator itr = entitiesByUniqueKey.values().iterator();
		while ( itr.hasNext() ) {
			if ( itr.next() == entity ) {
				itr.remove();
			}
		}
		// Clear all parent cache
		parentsByChild.clear();
		entitySnapshotsByKey.remove( key );
		nullifiableEntityKeys.remove( key );
		if( batchFetchQueue != null ) {
			getBatchFetchQueue().removeBatchLoadableEntityKey(key);
			getBatchFetchQueue().removeSubselect(key);
		}
		return entity;
	}
