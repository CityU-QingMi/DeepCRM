	@Override
	public boolean wasInsertedDuringTransaction(EntityPersister persister, Serializable id) {
		// again, we only really care if the entity is cached
		if ( persister.hasCache() ) {
			if ( insertedKeysMap != null ) {
				final List<Serializable> insertedEntityIds = insertedKeysMap.get( persister.getRootEntityName() );
				if ( insertedEntityIds != null ) {
					return insertedEntityIds.contains( id );
				}
			}
		}
		return false;
	}
