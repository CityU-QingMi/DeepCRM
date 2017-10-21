	@Override
	@SuppressWarnings({""})
	public List<Number> getRevisions(Class<?> cls, String entityName, Object primaryKey)
			throws IllegalArgumentException, NotAuditedException, IllegalStateException {
		// todo: if a class is not versioned from the beginning, there's a missing ADD rev - what then?
		cls = getTargetClassIfProxied( cls );
		checkNotNull( cls, "Entity class" );
		checkNotNull( entityName, "Entity name" );
		checkNotNull( primaryKey, "Primary key" );
		checkSession();

		return createQuery().forRevisionsOfEntity( cls, entityName, false, true )
				.addProjection( AuditEntity.revisionNumber() )
				.addOrder( AuditEntity.revisionNumber().asc() )
				.add( AuditEntity.id().eq( primaryKey ) )
				.getResultList();
	}
