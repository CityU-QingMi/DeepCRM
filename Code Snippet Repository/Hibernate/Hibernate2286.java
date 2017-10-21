	public void addBatchLoadableCollection(PersistentCollection collection, CollectionEntry ce) {
		final CollectionPersister persister = ce.getLoadedPersister();

		LinkedHashMap<CollectionEntry, PersistentCollection> map =  batchLoadableCollections.get( persister.getRole() );
		if ( map == null ) {
			map = new LinkedHashMap<>( 16 );
			batchLoadableCollections.put( persister.getRole(), map );
		}
		map.put( ce, collection );
	}
