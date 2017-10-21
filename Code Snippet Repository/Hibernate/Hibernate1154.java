	private void evict(Serializable id, CollectionPersister collectionPersister, EventSource session) {
		if ( LOG.isDebugEnabled() ) {
			LOG.debug( "Evict CollectionRegion " + collectionPersister.getRole() + " for id " + id );
		}
		AfterTransactionCompletionProcess afterTransactionProcess = new CollectionEvictCacheAction(
				collectionPersister,
				null,
				id,
				session
		).lockCache();
		session.getActionQueue().registerProcess( afterTransactionProcess );
	}
