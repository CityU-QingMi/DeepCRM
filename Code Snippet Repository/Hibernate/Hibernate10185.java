	private Type getPropertyType(SessionImplementor session, String entityName, String propertyName) {
		// rather than rely on QueryException from calling getPropertyType(), this allows a non-failure way
		// to determine whether to return null or lookup the value safely.
		final EntityPersister persister = session.getSessionFactory().getMetamodel().entityPersister( entityName );
		for ( String name : persister.getPropertyNames() ) {
			if ( name.equals( propertyName ) ) {
				return persister.getPropertyType( propertyName );
			}
		}
		return null;
	}
