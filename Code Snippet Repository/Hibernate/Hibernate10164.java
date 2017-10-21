	public AuditQuery forRevisionsOfEntity(Class<?> c, boolean selectEntitiesOnly, boolean selectDeletedEntities) {
		c = getTargetClassIfProxied( c );
		checkEntityAudited( c.getName() );
		return new RevisionsOfEntityQuery(
				enversService,
				auditReaderImplementor,
				c,
				selectEntitiesOnly,
				selectDeletedEntities
		);
	}
