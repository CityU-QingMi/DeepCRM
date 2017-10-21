	public AuditQuery forEntitiesAtRevision(Class<?> c, String entityName, Number revision, boolean includeDeletions) {
		checkNotNull( revision, "Entity revision" );
		checkPositive( revision, "Entity revision" );
		c = getTargetClassIfProxied( c );
		checkEntityAudited( entityName );
		return new EntitiesAtRevisionQuery(
				enversService,
				auditReaderImplementor,
				c,
				entityName,
				revision,
				includeDeletions
		);
	}
