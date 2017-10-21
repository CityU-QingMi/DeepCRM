	public PreCollectionRecreateEvent(
			CollectionPersister collectionPersister,
			PersistentCollection collection,
			EventSource source) {
		super(
				collectionPersister,
				collection,
				source,
				collection.getOwner(),
				getOwnerIdOrNull( collection.getOwner(), source )
		);
	}
