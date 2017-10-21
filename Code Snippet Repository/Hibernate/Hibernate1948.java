	public static boolean isTransient(String entityName, Object entity, Boolean assumed, SharedSessionContractImplementor session) {
		if ( entity == LazyPropertyInitializer.UNFETCHED_PROPERTY ) {
			// an unfetched association can only point to
			// an entity that already exists in the db
			return false;
		}

		// let the interceptor inspect the instance to decide
		Boolean isUnsaved = session.getInterceptor().isTransient( entity );
		if ( isUnsaved != null ) {
			return isUnsaved;
		}

		// let the persister inspect the instance to decide
		final EntityPersister persister = session.getEntityPersister( entityName, entity );
		isUnsaved = persister.isTransient( entity, session );
		if ( isUnsaved != null ) {
			return isUnsaved;
		}

		// we use the assumed value, if there is one, to avoid hitting
		// the database
		if ( assumed != null ) {
			return assumed;
		}

		// hit the database, after checking the session cache for a snapshot
		final Object[] snapshot = session.getPersistenceContext().getDatabaseSnapshot(
				persister.getIdentifier( entity, session ),
				persister
		);
		return snapshot == null;

	}
