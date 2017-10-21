	public void postInitialize(PersistentCollection collection) throws HibernateException {
		snapshot = getLoadedPersister().isMutable()
				? collection.getSnapshot( getLoadedPersister() )
				: null;
		collection.setSnapshot(loadedKey, role, snapshot);
		if ( getLoadedPersister().getBatchSize() > 1 ) {
			( (AbstractPersistentCollection) collection ).getSession()
					.getPersistenceContext()
					.getBatchFetchQueue()
					.removeBatchLoadableCollection( this );
		}
	}
