	public void performBasicComparison(SessionFactoryImplementor sf, OuterJoinLoadable persister) {
		// todo : allow these to be passed in by tests?
		final LoadQueryInfluencers influencers = LoadQueryInfluencers.NONE;
		final LockMode lockMode = LockMode.NONE;
		final int batchSize = 1;

		// legacy Loader-based contracts...
		final EntityJoinWalker walker = new EntityJoinWalker(
				persister,
				persister.getKeyColumnNames(),
				batchSize,
				lockMode,
				sf,
				influencers
		);
//		final EntityLoader loader = new EntityLoader( persister, lockMode, sf, influencers );

		LoadPlan plan = buildLoadPlan( sf, persister, influencers, lockMode );
		LoadQueryDetails details = BatchingLoadQueryDetailsFactory.INSTANCE.makeEntityLoadQueryDetails(
				plan,
				persister.getKeyColumnNames(),
				new QueryBuildingParametersImpl(
						influencers,
						batchSize,
						lockMode,
						null

				),
				sf
		);

		compare( walker, details );
	}
