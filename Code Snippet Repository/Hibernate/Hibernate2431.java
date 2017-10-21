	private Object processCachedEntry(
			final LoadEvent event,
			final EntityPersister persister,
			final Object ce,
			final SessionImplementor source,
			final EntityKey entityKey) {

		CacheEntry entry = (CacheEntry) persister.getCacheEntryStructure().destructure( ce, source.getFactory() );
		if(entry.isReferenceEntry()) {
			if( event.getInstanceToLoad() != null ) {
				throw new HibernateException(
						"Attempt to load entity [%s] from cache using provided object instance, but cache " +
								"is storing references: "+ event.getEntityId());
			}
			else {
				return convertCacheReferenceEntryToEntity( (ReferenceCacheEntryImpl) entry,
						event.getSession(), entityKey);
			}
		}
		else {
			Object entity = convertCacheEntryToEntity( entry, event.getEntityId(), persister, event, entityKey );

			if ( !persister.isInstance( entity ) ) {
				throw new WrongClassException(
						"loaded object was of wrong class " + entity.getClass(),
						event.getEntityId(),
						persister.getEntityName()
				);
			}

			return entity;
		}
	}
