	@SuppressWarnings( {""})
	public EntityLoadContext getEntityLoadContext(ResultSet resultSet) {
		EntityLoadContext context = null;
		if ( entityLoadContexts == null ) {
			entityLoadContexts = new IdentityHashMap<ResultSet, EntityLoadContext>( 8 );
		}
		else {
			context = entityLoadContexts.get( resultSet );
		}
		if ( context == null ) {
			context = new EntityLoadContext( this, resultSet );
			entityLoadContexts.put( resultSet, context );
		}
		return context;
	}
