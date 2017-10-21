	public AuditQuery forRevisionsOfEntity(
			Class<?> c,
			String entityName,
			boolean selectEntitiesOnly,
			boolean selectDeletedEntities) {
		c = getTargetClassIfProxied( c );
		checkEntityAudited( entityName );
		return new RevisionsOfEntityQuery(
				enversService,
				auditReaderImplementor,
				c,
				entityName,
				selectEntitiesOnly,
				selectDeletedEntities
		);
	}
