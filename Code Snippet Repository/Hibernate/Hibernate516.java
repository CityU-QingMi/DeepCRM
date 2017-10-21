	protected CollectionAction(
			final CollectionPersister persister,
			final PersistentCollection collection, 
			final Serializable key, 
			final SharedSessionContractImplementor session) {
		this.persister = persister;
		this.session = session;
		this.key = key;
		this.collectionRole = persister.getRole();
		this.collection = collection;
	}
