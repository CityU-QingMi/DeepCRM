	public RevisionsOfEntityQuery(
			EnversService enversService,
			AuditReaderImplementor versionsReader,
			Class<?> cls,
			boolean selectEntitiesOnly,
			boolean selectDeletedEntities) {
		super( enversService, versionsReader, cls );

		this.selectEntitiesOnly = selectEntitiesOnly;
		this.selectDeletedEntities = selectDeletedEntities;
	}
