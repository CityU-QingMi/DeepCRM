	public SortedSetCollectionInitializor(
			EnversService enversService,
			AuditReaderImplementor versionsReader,
			RelationQueryGenerator queryGenerator,
			Object primaryKey,
			Number revision,
			boolean removed,
			Class<? extends SortedSet> collectionClass,
			MiddleComponentData elementComponentData,
			Comparator comparator) {
		super(
				enversService,
				versionsReader,
				queryGenerator,
				primaryKey,
				revision,
				removed,
				collectionClass,
				elementComponentData
		);
		this.comparator = comparator;
	}
