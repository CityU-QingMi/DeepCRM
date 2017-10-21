	public PersistentCollection getLoadingCollection(final CollectionPersister persister, final Serializable key) {
		final EntityMode em = persister.getOwnerEntityPersister().getEntityMetamodel().getEntityMode();
		final CollectionKey collectionKey = new CollectionKey( persister, key, em );
		if ( LOG.isTraceEnabled() ) {
			LOG.tracev( "Starting attempt to find loading collection [{0}]",
					MessageHelper.collectionInfoString( persister.getRole(), key ) );
		}
		final LoadingCollectionEntry loadingCollectionEntry = loadContexts.locateLoadingCollectionEntry( collectionKey );
		if ( loadingCollectionEntry == null ) {
			// look for existing collection as part of the persistence context
			PersistentCollection collection = loadContexts.getPersistenceContext().getCollection( collectionKey );
			if ( collection != null ) {
				if ( collection.wasInitialized() ) {
					LOG.trace( "Collection already initialized; ignoring" );
					// ignore this row of results! Note the early exit
					return null;
				}
				LOG.trace( "Collection not yet initialized; initializing" );
			}
			else {
				final Object owner = loadContexts.getPersistenceContext().getCollectionOwner( key, persister );
				final boolean newlySavedEntity = owner != null
						&& loadContexts.getPersistenceContext().getEntry( owner ).getStatus() != Status.LOADING;
				if ( newlySavedEntity ) {
					// important, to account for newly saved entities in query
					// todo : some kind of check for new status...
					LOG.trace( "Owning entity already loaded; ignoring" );
					return null;
				}
				// create one
				LOG.tracev( "Instantiating new collection [key={0}, rs={1}]", key, resultSet );
				collection = persister.getCollectionType().instantiate(
						loadContexts.getPersistenceContext().getSession(), persister, key );
			}
			collection.beforeInitialize( persister, -1 );
			collection.beginRead();
			localLoadingCollectionKeys.add( collectionKey );
			loadContexts.registerLoadingCollectionXRef( collectionKey, new LoadingCollectionEntry( resultSet, persister, key, collection ) );
			return collection;
		}
		if ( loadingCollectionEntry.getResultSet() == resultSet ) {
			LOG.trace( "Found loading collection bound to current result set processing; reading row" );
			return loadingCollectionEntry.getCollection();
		}
		// ignore this row, the collection is in process of
		// being loaded somewhere further "up" the stack
		LOG.trace( "Collection is already being initialized; ignoring row" );
		return null;
	}
