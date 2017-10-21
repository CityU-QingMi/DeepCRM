	public Serializable[] getEntityBatch(
			final EntityPersister persister,
			final Serializable id,
			final int batchSize,
			final EntityMode entityMode) {
		Serializable[] ids = new Serializable[batchSize];
		ids[0] = id; //first element of array is reserved for the actual instance we are loading!
		int i = 1;
		int end = -1;
		boolean checkForEnd = false;

		// TODO: this needn't exclude subclasses...

		LinkedHashSet<EntityKey> set =  batchLoadableEntityKeys.get( persister.getEntityName() );
		if ( set != null ) {
			for ( EntityKey key : set ) {
				if ( checkForEnd && i == end ) {
					//the first id found after the given id
					return ids;
				}
				if ( persister.getIdentifierType().isEqual( id, key.getIdentifier() ) ) {
					end = i;
				}
				else {
					if ( !isCached( key, persister ) ) {
						ids[i++] = key.getIdentifier();
					}
				}
				if ( i == batchSize ) {
					i = 1; // end of array, start filling again from start
					if ( end != -1 ) {
						checkForEnd = true;
					}
				}
			}
		}
		return ids; //we ran out of ids to try
	}
