	private void prepareCollectionFlushes(PersistenceContext persistenceContext) throws HibernateException {

		// Initialize dirty flags for arrays + collections with composite elements
		// and reset reached, doupdate, etc.

		LOG.debug( "Dirty checking collections" );

		for ( Map.Entry<PersistentCollection,CollectionEntry> entry :
				IdentityMap.concurrentEntries( (Map<PersistentCollection,CollectionEntry>) persistenceContext.getCollectionEntries() )) {
			entry.getValue().preFlush( entry.getKey() );
		}
	}
