	public void cleanup(ResultSet resultSet) {
		if ( collectionLoadContexts != null ) {
			final CollectionLoadContext collectionLoadContext = collectionLoadContexts.remove( resultSet );
			collectionLoadContext.cleanup();
		}
		if ( entityLoadContexts != null ) {
			final EntityLoadContext entityLoadContext = entityLoadContexts.remove( resultSet );
			entityLoadContext.cleanup();
		}
	}
