	public void afterAction(PersistentCollection collection) {
		loadedKey = getCurrentKey();
		setLoadedPersister( getCurrentPersister() );

		boolean resnapshot = collection.wasInitialized() &&
				( isDoremove() || isDorecreate() || isDoupdate() );
		if ( resnapshot ) {
			snapshot = loadedPersister==null || !loadedPersister.isMutable() ?
					null :
					collection.getSnapshot(loadedPersister); //re-snapshot
		}

		collection.postAction();
	}
