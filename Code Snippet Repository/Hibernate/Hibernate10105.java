	public AddWorkUnit(
			SessionImplementor sessionImplementor,
			String entityName,
			EnversService enversService,
			Serializable id, EntityPersister entityPersister, Object[] state) {
		super( sessionImplementor, entityName, enversService, id, RevisionType.ADD );

		this.data = new HashMap<>();
		this.state = state;
		this.enversService.getEntitiesConfigurations().get( getEntityName() ).getPropertyMapper().map(
				sessionImplementor,
				data,
				entityPersister.getPropertyNames(),
				state,
				null
		);
	}
