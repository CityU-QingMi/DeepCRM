	public EntitiesAtRevisionQuery(
			EnversService enversService,
			AuditReaderImplementor versionsReader,
			Class<?> cls,
			Number revision,
			boolean includeDeletions) {
		super( enversService, versionsReader, cls );
		this.revision = revision;
		this.includeDeletions = includeDeletions;
	}
