	public AddWorkUnit(
			SessionImplementor sessionImplementor,
			String entityName,
			EnversService enversService,
			Serializable id,
			Map<String, Object> data) {
		super( sessionImplementor, entityName, enversService, id, RevisionType.ADD );

		this.data = data;
		final String[] propertyNames = sessionImplementor.getFactory().getMetamodel()
				.entityPersister( getEntityName() )
				.getPropertyNames();
		this.state = ArraysTools.mapToArray( data, propertyNames );
	}
