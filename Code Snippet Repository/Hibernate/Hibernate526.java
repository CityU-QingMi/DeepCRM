	public CollectionRemoveAction(
				final PersistentCollection collection,
				final CollectionPersister persister,
				final Serializable id,
				final boolean emptySnapshot,
				final SharedSessionContractImplementor session) {
		super( persister, collection, id, session );
		if ( collection == null ) {
			throw new AssertionFailure("collection == null");
		}
		this.emptySnapshot = emptySnapshot;
		// the loaded owner will be set to null after the collection is removed,
		// so capture its value as the affected owner so it is accessible to
		// both pre- and post- events
		this.affectedOwner = session.getPersistenceContext().getLoadedCollectionOwnerOrNull( collection );
	}
