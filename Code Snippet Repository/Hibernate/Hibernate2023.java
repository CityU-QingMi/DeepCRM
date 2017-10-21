	@Override
	public Object getLoadedCollectionOwnerOrNull(PersistentCollection collection) {
		final CollectionEntry ce = getCollectionEntry( collection );
		if ( ce.getLoadedPersister() == null ) {
			return null;
		}

		Object loadedOwner = null;
		// TODO: an alternative is to check if the owner has changed; if it hasn't then
		// return collection.getOwner()
		final Serializable entityId = getLoadedCollectionOwnerIdOrNull( ce );
		if ( entityId != null ) {
			loadedOwner = getCollectionOwner( entityId, ce.getLoadedPersister() );
		}
		return loadedOwner;
	}
