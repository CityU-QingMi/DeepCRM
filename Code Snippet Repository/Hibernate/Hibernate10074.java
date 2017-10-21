	@Override
	public String getEntityName(Object primaryKey, Number revision, Object entity) throws HibernateException {
		checkNotNull( primaryKey, "Primary key" );
		checkNotNull( revision, "Entity revision" );
		checkPositive( revision, "Entity revision" );
		checkNotNull( entity, "Entity" );
		checkSession();

		// Unwrap if necessary
		if ( entity instanceof HibernateProxy ) {
			entity = ( (HibernateProxy) entity ).getHibernateLazyInitializer().getImplementation();
		}
		if ( firstLevelCache.containsEntityName( primaryKey, revision, entity ) ) {
			// it's on envers FLC!
			return firstLevelCache.getFromEntityNameCache( primaryKey, revision, entity );
		}
		else {
			throw new HibernateException(
					"Envers can't resolve entityName for historic entity. The id, revision and entity is not on envers first level cache."
			);
		}
	}
