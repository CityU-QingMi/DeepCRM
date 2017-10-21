	public void cleanup() {
		if ( collectionLoadContexts != null ) {
			for ( CollectionLoadContext collectionLoadContext : collectionLoadContexts.values() ) {
				LOG.failSafeCollectionsCleanup( collectionLoadContext );
				collectionLoadContext.cleanup();
			}
			collectionLoadContexts.clear();
		}
		if ( entityLoadContexts != null ) {
			for ( EntityLoadContext entityLoadContext : entityLoadContexts.values() ) {
				LOG.failSafeEntitiesCleanup( entityLoadContext );
				entityLoadContext.cleanup();
			}
			entityLoadContexts.clear();
		}
	}
