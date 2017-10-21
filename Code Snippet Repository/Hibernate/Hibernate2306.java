	public CollectionEntry(CollectionPersister loadedPersister, Serializable loadedKey) {
		// detached collection wrappers that get found + reattached
		// during flush shouldn't be ignored
		ignore = false;

		//collection.clearDirty()

		this.loadedKey = loadedKey;
		setLoadedPersister(loadedPersister);
	}
