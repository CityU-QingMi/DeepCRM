	public PostCollectionUpdateEvent(
			CollectionPersister collectionPersister,
			PersistentCollection collection,
			EventSource source) {
		super(
				collectionPersister,
				collection,
				source,
				getLoadedOwnerOrNull( collection, source ),
				getLoadedOwnerIdOrNull( collection, source )
		);
	}
