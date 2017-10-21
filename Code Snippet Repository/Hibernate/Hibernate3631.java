	public UniqueEntityLoader buildLoader(
			OuterJoinLoadable persister,
			int batchSize,
			LockMode lockMode,
			SessionFactoryImplementor factory,
			LoadQueryInfluencers influencers) {
		if ( batchSize <= 1 ) {
			// no batching
			return buildNonBatchingLoader( persister, lockMode, factory, influencers );
		}
		return buildBatchingLoader( persister, batchSize, lockMode, factory, influencers );
	}
