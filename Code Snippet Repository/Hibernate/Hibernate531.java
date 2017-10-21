	@Override
	public void execute() throws HibernateException {
		final Serializable id = getKey();
		final SharedSessionContractImplementor session = getSession();
		final CollectionPersister persister = getPersister();
		final PersistentCollection collection = getCollection();
		final boolean affectedByFilters = persister.isAffectedByEnabledFilters( session );

		preUpdate();

		if ( !collection.wasInitialized() ) {
			if ( !collection.hasQueuedOperations() ) {
				throw new AssertionFailure( "no queued adds" );
			}
			//do nothing - we only need to notify the cache... 
		}
		else if ( !affectedByFilters && collection.empty() ) {
			if ( !emptySnapshot ) {
				persister.remove( id, session );
			}
		}
		else if ( collection.needsRecreate( persister ) ) {
			if ( affectedByFilters ) {
				throw new HibernateException(
						"cannot recreate collection while filter is enabled: " +
								MessageHelper.collectionInfoString( persister, collection, id, session )
				);
			}
			if ( !emptySnapshot ) {
				persister.remove( id, session );
			}
			persister.recreate( collection, id, session );
		}
		else {
			persister.deleteRows( collection, id, session );
			persister.updateRows( collection, id, session );
			persister.insertRows( collection, id, session );
		}

		getSession().getPersistenceContext().getCollectionEntry( collection ).afterAction( collection );
		evict();
		postUpdate();

		if ( getSession().getFactory().getStatistics().isStatisticsEnabled() ) {
			getSession().getFactory().getStatistics().updateCollection( getPersister().getRole() );
		}
	}
