	@Override
	public EntityGraph<?> createEntityGraph(String graphName) {
		checkOpen();
		final EntityGraph named = getEntityManagerFactory().findEntityGraphByName( graphName );
		if ( named == null ) {
			return null;
		}

		if ( EntityGraphImplementor.class.isInstance( named ) ) {
			return ( (EntityGraphImplementor) named ).makeMutableCopy();
		}
		else {
			return named;
		}
	}
