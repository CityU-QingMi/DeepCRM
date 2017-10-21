	@Override
	@SuppressWarnings( {""})
	public EntityPersister createEntityPersister(
			PersistentClass entityBinding,
			EntityRegionAccessStrategy entityCacheAccessStrategy,
			NaturalIdRegionAccessStrategy naturalIdCacheAccessStrategy,
			PersisterCreationContext creationContext) throws HibernateException {
		// If the metadata for the entity specified an explicit persister class, use it...
		Class<? extends EntityPersister> persisterClass = entityBinding.getEntityPersisterClass();
		if ( persisterClass == null ) {
			// Otherwise, use the persister class indicated by the PersisterClassResolver service
			persisterClass = serviceRegistry.getService( PersisterClassResolver.class ).getEntityPersisterClass( entityBinding );
		}

		return createEntityPersister(
				persisterClass,
				entityBinding,
				entityCacheAccessStrategy,
				naturalIdCacheAccessStrategy,
				creationContext
		);
	}
