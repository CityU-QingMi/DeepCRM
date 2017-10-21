	private void endLoadingCollections(CollectionPersister persister, List<LoadingCollectionEntry> matchedCollectionEntries) {
		final boolean debugEnabled = LOG.isDebugEnabled();
		if ( matchedCollectionEntries == null ) {
			if ( debugEnabled ) {
				LOG.debugf( "No collections were found in result set for role: %s", persister.getRole() );
			}
			return;
		}

		final int count = matchedCollectionEntries.size();
		if ( debugEnabled ) {
			LOG.debugf( "%s collections were found in result set for role: %s", count, persister.getRole() );
		}

		for ( LoadingCollectionEntry matchedCollectionEntry : matchedCollectionEntries ) {
			endLoadingCollection( matchedCollectionEntry, persister );
		}

		if ( debugEnabled ) {
			LOG.debugf( "%s collections initialized for role: %s", count, persister.getRole() );
		}
	}
