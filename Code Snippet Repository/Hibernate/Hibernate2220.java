	public PersistentCollection locateLoadingCollection(CollectionPersister persister, Serializable ownerKey) {
		final LoadingCollectionEntry lce = locateLoadingCollectionEntry( new CollectionKey( persister, ownerKey ) );
		if ( lce != null ) {
			if ( LOG.isTraceEnabled() ) {
				LOG.tracef(
						"Returning loading collection: %s",
						MessageHelper.collectionInfoString( persister, ownerKey, getSession().getFactory() )
				);
			}
			return lce.getCollection();
		}
		return null;
	}
