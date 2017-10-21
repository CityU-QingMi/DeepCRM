	public void putOnEntityNameCache(Object id, Number revision, Object entity, String entityName) {
		LOG.debugf(
				"Caching entityName on First Level Cache:  - primaryKey:%s - revision:%s - entity:%s -> entityName:%s",
				id,
				revision,
				entity.getClass().getName(),
				entityName
		);
		entityNameCache.put( make( id, revision, entity ), entityName );
	}
