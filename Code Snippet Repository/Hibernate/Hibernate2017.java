	public StatefulPersistenceContext(SharedSessionContractImplementor session) {
		this.session = session;

		entitiesByKey = new HashMap<>( INIT_COLL_SIZE );
		entitiesByUniqueKey = new HashMap<>( INIT_COLL_SIZE );
		//noinspection unchecked
		proxiesByKey = new ConcurrentReferenceHashMap<>(
				INIT_COLL_SIZE,
				.75f,
				1,
				ConcurrentReferenceHashMap.ReferenceType.STRONG,
				ConcurrentReferenceHashMap.ReferenceType.WEAK,
				null
		);
		entitySnapshotsByKey = new HashMap<>( INIT_COLL_SIZE );

		entityEntryContext = new EntityEntryContext( this );
//		entityEntries = IdentityMap.instantiateSequenced( INIT_COLL_SIZE );
		collectionEntries = IdentityMap.instantiateSequenced( INIT_COLL_SIZE );
		parentsByChild = new IdentityHashMap<>( INIT_COLL_SIZE );

		collectionsByKey = new HashMap<>( INIT_COLL_SIZE );
		arrayHolders = new IdentityHashMap<>( INIT_COLL_SIZE );

		nullifiableEntityKeys = new HashSet<>();

		initTransientState();
	}
