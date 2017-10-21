	public PersistentCollectionChangeWorkUnit(
			SessionImplementor sessionImplementor,
			String entityName,
			EnversService enversService,
			PersistentCollection collection,
			CollectionEntry collectionEntry,
			Serializable snapshot,
			Serializable id,
			String referencingPropertyName) {
		super(
				sessionImplementor,
				entityName,
				enversService,
				new PersistentCollectionChangeWorkUnitId( id, collectionEntry.getRole() ),
				RevisionType.MOD
		);

		this.referencingPropertyName = referencingPropertyName;

		collectionChanges = enversService.getEntitiesConfigurations().get( getEntityName() ).getPropertyMapper()
				.mapCollectionChanges( sessionImplementor, referencingPropertyName, collection, snapshot, id );
	}
