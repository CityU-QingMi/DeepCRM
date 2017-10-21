	private void endLoadingCollection(LoadingCollectionEntry lce, CollectionPersister persister) {
		LOG.tracev( "Ending loading collection [{0}]", lce );
		final SharedSessionContractImplementor session = getLoadContext().getPersistenceContext().getSession();

		// warning: can cause a recursive calls! (proxy initialization)
		final boolean hasNoQueuedAdds = lce.getCollection().endRead();

		if ( persister.getCollectionType().hasHolder() ) {
			getLoadContext().getPersistenceContext().addCollectionHolder( lce.getCollection() );
		}

		CollectionEntry ce = getLoadContext().getPersistenceContext().getCollectionEntry( lce.getCollection() );
		if ( ce == null ) {
			ce = getLoadContext().getPersistenceContext().addInitializedCollection( persister, lce.getCollection(), lce.getKey() );
		}
		else {
			ce.postInitialize( lce.getCollection() );
//			if (ce.getLoadedPersister().getBatchSize() > 1) { // not the best place for doing this, moved into ce.postInitialize
//				getLoadContext().getPersistenceContext().getBatchFetchQueue().removeBatchLoadableCollection(ce); 
//			}
		}


		// add to cache if:
		boolean addToCache =
				// there were no queued additions
				hasNoQueuedAdds
				// and the role has a cache
				&& persister.hasCache()
				// and this is not a forced initialization during flush
				&& session.getCacheMode().isPutEnabled() && !ce.isDoremove();
		if ( addToCache ) {
			addCollectionToCache( lce, persister );
		}

		if ( LOG.isDebugEnabled() ) {
			LOG.debugf(
					"Collection fully initialized: %s",
					MessageHelper.collectionInfoString( persister, lce.getCollection(), lce.getKey(), session )
			);
		}
		if ( session.getFactory().getStatistics().isStatisticsEnabled() ) {
			session.getFactory().getStatistics().loadCollection( persister.getRole() );
		}
	}
