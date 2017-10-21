	private static void processNeverReferencedCollection(PersistentCollection coll, SessionImplementor session)
			throws HibernateException {
		final PersistenceContext persistenceContext = session.getPersistenceContext();
		final CollectionEntry entry = persistenceContext.getCollectionEntry( coll );

		if ( LOG.isDebugEnabled() ) {
			LOG.debugf(
					"Found collection with unloaded owner: %s",
					MessageHelper.collectionInfoString( 
							entry.getLoadedPersister(),
							coll,
							entry.getLoadedKey(),
							session
					)
			);
		}

		entry.setCurrentPersister( entry.getLoadedPersister() );
		entry.setCurrentKey( entry.getLoadedKey() );

		prepareCollectionForUpdate( coll, entry, session.getFactory() );

	}
