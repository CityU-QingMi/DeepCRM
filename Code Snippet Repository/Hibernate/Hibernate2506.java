	public AbstractCollectionEvent( CollectionPersister collectionPersister,
					PersistentCollection collection,
					EventSource source,
					Object affectedOwner,
					Serializable affectedOwnerId) {
		super(source);
		this.collection = collection;
		this.affectedOwner = affectedOwner;
		this.affectedOwnerId = affectedOwnerId;
		this.affectedOwnerEntityName =
				getAffectedOwnerEntityName( collectionPersister, affectedOwner, source );
	}
