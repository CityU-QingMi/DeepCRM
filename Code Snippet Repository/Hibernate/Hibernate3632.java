	public UniqueEntityLoader buildLoader(
			OuterJoinLoadable persister,
			int batchSize,
			LockOptions lockOptions,
			SessionFactoryImplementor factory,
			LoadQueryInfluencers influencers) {
		if ( batchSize <= 1 ) {
			// no batching
			return buildNonBatchingLoader( persister, lockOptions, factory, influencers );
		}
		return buildBatchingLoader( persister, batchSize, lockOptions, factory, influencers );
	}
