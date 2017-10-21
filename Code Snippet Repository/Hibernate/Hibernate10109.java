	public CollectionChangeWorkUnit(
			SessionImplementor session,
			String entityName,
			String collectionPropertyName,
			EnversService enversService,
			Serializable id,
			Object entity) {
		super( session, entityName, enversService, id, RevisionType.MOD );

		this.entity = entity;
		this.collectionPropertyName = collectionPropertyName;
	}
