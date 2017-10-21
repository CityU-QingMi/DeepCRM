	public ToOneDelegateSessionImplementor(
			AuditReaderImplementor versionsReader,
			Class<?> entityClass,
			Object entityId,
			Number revision,
			boolean removed,
			EnversService enversService) {
		super( versionsReader.getSessionImplementor() );
		this.versionsReader = versionsReader;
		this.entityClass = entityClass;
		this.entityId = entityId;
		this.revision = revision;
		this.removed = removed;
		this.enversService = enversService;
	}
