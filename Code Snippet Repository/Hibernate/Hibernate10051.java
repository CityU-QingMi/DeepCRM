	public SortedMapCollectionInitializor(
			EnversService enversService,
			AuditReaderImplementor versionsReader,
			RelationQueryGenerator queryGenerator,
			Object primaryKey, Number revision, boolean removed,
			Class<? extends SortedMap> collectionClass,
			MiddleComponentData elementComponentData,
			MiddleComponentData indexComponentData, Comparator comparator) {
		super(
				enversService,
				versionsReader,
				queryGenerator,
				primaryKey,
				revision,
				removed,
				collectionClass,
				elementComponentData,
				indexComponentData
		);
		this.comparator = comparator;
	}
