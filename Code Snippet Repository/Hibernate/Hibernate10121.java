	public PersistentCollectionChangeWorkUnit(
			SessionImplementor sessionImplementor,
			String entityName,
			EnversService enversService,
			Serializable id,
			List<PersistentCollectionChangeData> collectionChanges,
			String referencingPropertyName) {
		super( sessionImplementor, entityName, enversService, id, RevisionType.MOD );

		this.collectionChanges = collectionChanges;
		this.referencingPropertyName = referencingPropertyName;
	}
