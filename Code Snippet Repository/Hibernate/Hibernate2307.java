	public CollectionEntry(PersistentCollection collection, SessionFactoryImplementor factory) throws MappingException {
		// detached collections that get found + reattached
		// during flush shouldn't be ignored
		ignore = false;

		loadedKey = collection.getKey();
		setLoadedPersister( factory.getMetamodel().collectionPersister( collection.getRole() ) );

		snapshot = collection.getStoredSnapshot();
	}
