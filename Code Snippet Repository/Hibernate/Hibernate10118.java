	public ModWorkUnit(
			SessionImplementor sessionImplementor,
			String entityName,
			EnversService enversService,
			Serializable id,
			EntityPersister entityPersister,
			Object[] newState,
			Object[] oldState) {
		super( sessionImplementor, entityName, enversService, id, RevisionType.MOD );

		this.entityPersister = entityPersister;
		this.oldState = oldState;
		this.newState = newState;
		this.data = new HashMap<>();
		this.changes = enversService.getEntitiesConfigurations().get( getEntityName() ).getPropertyMapper().map(
				sessionImplementor,
				data,
				entityPersister.getPropertyNames(),
				newState,
				oldState
		);
	}
