	protected static String getAffectedOwnerEntityName(CollectionPersister collectionPersister, Object affectedOwner, EventSource source ) {

		// collectionPersister should not be null, but we don't want to throw
		// an exception if it is null
		String entityName =
				( collectionPersister == null ? null : collectionPersister.getOwnerEntityPersister().getEntityName() );
		if ( affectedOwner != null ) {
			EntityEntry ee = source.getPersistenceContext().getEntry( affectedOwner );
			if ( ee != null && ee.getEntityName() != null) {
				entityName = ee.getEntityName();
			}
		}	
		return entityName;
	}
