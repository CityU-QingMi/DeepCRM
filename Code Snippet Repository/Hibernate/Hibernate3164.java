	@Override
	@SuppressWarnings("")
	public EntityGraph<?> getEntityGraph(String graphName) {
		checkOpen();
		final EntityGraph named = getEntityManagerFactory().findEntityGraphByName( graphName );
		if ( named == null ) {
			throw new IllegalArgumentException( "Could not locate EntityGraph with given name : " + graphName );
		}
		return named;
	}
