	LoadingCollectionEntry locateLoadingCollectionEntry(CollectionKey key) {
		if ( xrefLoadingCollectionEntries == null ) {
			return null;
		}
		LOG.tracev( "Attempting to locate loading collection entry [{0}] in any result-set context", key );
		final LoadingCollectionEntry rtn = xrefLoadingCollectionEntries.get( key );
		if ( rtn == null ) {
			LOG.tracev( "Collection [{0}] not located in load context", key );
		}
		else {
			LOG.tracev( "Collection [{0}] located in load context", key );
		}
		return rtn;
	}
