	public CollectionEntry(CollectionPersister persister, PersistentCollection collection) {
		// new collections that get found + wrapped
		// during flush shouldn't be ignored
		ignore = false;

		collection.clearDirty(); //a newly wrapped collection is NOT dirty (or we get unnecessary version updates)

		snapshot = persister.isMutable() ?
				collection.getSnapshot(persister) :
				null;
		collection.setSnapshot(loadedKey, role, snapshot);
	}
