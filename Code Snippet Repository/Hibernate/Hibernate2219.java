	public CollectionLoadContext getCollectionLoadContext(ResultSet resultSet) {
		CollectionLoadContext context = null;
		if ( collectionLoadContexts == null ) {
			collectionLoadContexts = new IdentityHashMap<ResultSet, CollectionLoadContext>( 8 );
		}
		else {
			context = collectionLoadContexts.get(resultSet);
		}
		if ( context == null ) {
			LOG.tracev( "Constructing collection load context for result set [{0}]", resultSet );
			context = new CollectionLoadContext( this, resultSet );
			collectionLoadContexts.put( resultSet, context );
		}
		return context;
	}
