	protected void reattachCollection(PersistentCollection collection, CollectionType type)
	throws HibernateException {
		if ( collection.wasInitialized() ) {
			CollectionPersister collectionPersister = getSession().getFactory()
			.getCollectionPersister( type.getRole() );
			getSession().getPersistenceContext()
				.addInitializedDetachedCollection( collectionPersister, collection );
		}
		else {
			if ( !isCollectionSnapshotValid(collection) ) {
				throw new HibernateException( "could not reassociate uninitialized transient collection" );
			}
			CollectionPersister collectionPersister = getSession().getFactory()
					.getCollectionPersister( collection.getRole() );
			getSession().getPersistenceContext()
				.addUninitializedDetachedCollection( collectionPersister, collection );
		}
	}
