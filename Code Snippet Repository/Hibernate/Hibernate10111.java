	public DelWorkUnit(
			SessionImplementor sessionImplementor,
			String entityName,
			EnversService enversService,
			Serializable id,
			EntityPersister entityPersister,
			Object[] state) {
		super( sessionImplementor, entityName, enversService, id, RevisionType.DEL );

		this.state = state;
		this.entityPersister = entityPersister;
		this.propertyNames = entityPersister.getPropertyNames();
	}
