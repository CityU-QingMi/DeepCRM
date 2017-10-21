	public AbstractCollectionInitializor(
			EnversService enversService,
			AuditReaderImplementor versionsReader,
			RelationQueryGenerator queryGenerator,
			Object primaryKey, Number revision, boolean removed) {
		this.versionsReader = versionsReader;
		this.queryGenerator = queryGenerator;
		this.primaryKey = primaryKey;
		this.revision = revision;
		this.removed = removed;

		entityInstantiator = new EntityInstantiator( enversService, versionsReader );
	}
