	public RevisionsOfEntityQuery(
			EnversService enversService,
			AuditReaderImplementor versionsReader,
			Class<?> cls, String entityName,
			boolean selectEntitiesOnly,
			boolean selectDeletedEntities) {
		super( enversService, versionsReader, cls, entityName );

		this.selectEntitiesOnly = selectEntitiesOnly;
		this.selectDeletedEntities = selectDeletedEntities;
	}
