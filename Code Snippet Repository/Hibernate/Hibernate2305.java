	public CollectionEntry(
			final PersistentCollection collection,
			final CollectionPersister loadedPersister,
			final Serializable loadedKey,
			final boolean ignore
	) {
		this.ignore=ignore;

		//collection.clearDirty()

		this.loadedKey = loadedKey;
		setLoadedPersister(loadedPersister);

		collection.setSnapshot(loadedKey, role, null);

		//postInitialize() will be called after initialization
	}
