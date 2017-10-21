	private void handleEmptyCollections(
			final Serializable[] keys,
			final Object resultSetId,
			final SharedSessionContractImplementor session) {

		if ( keys != null ) {
			final boolean debugEnabled = LOG.isDebugEnabled();
			// this is a collection initializer, so we must create a collection
			// for each of the passed-in keys, to account for the possibility
			// that the collection is empty and has no rows in the result set
			CollectionPersister[] collectionPersisters = getCollectionPersisters();
			for ( CollectionPersister collectionPersister : collectionPersisters ) {
				for ( Serializable key : keys ) {
					//handle empty collections
					if ( debugEnabled ) {
						LOG.debugf(
								"Result set contains (possibly empty) collection: %s",
								MessageHelper.collectionInfoString( collectionPersister, key, getFactory() )
						);
					}

					session.getPersistenceContext()
							.getLoadContexts()
							.getCollectionLoadContext( (ResultSet) resultSetId )
							.getLoadingCollection( collectionPersister, key );
				}
			}
		}

		// else this is not a collection initializer (and empty collections will
		// be detected by looking for the owner's identifier in the result set)
	}
