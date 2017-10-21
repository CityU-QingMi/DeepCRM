	public AbstractPreDatabaseOperationEvent(
			EventSource source,
			Object entity,
			Serializable id,
			EntityPersister persister) {
		super( source );
		this.entity = entity;
		this.id = id;
		this.persister = persister;
	}
