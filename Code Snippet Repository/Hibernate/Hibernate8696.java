	private void doCompare(SessionFactoryImplementor sf, OuterJoinLoadable persister) {
		final LoadQueryInfluencers influencers = LoadQueryInfluencers.NONE;
		final LockMode lockMode = LockMode.NONE;
		final int batchSize = 1;

		final EntityJoinWalker walker = new EntityJoinWalker(
				persister,
				persister.getKeyColumnNames(),
				batchSize,
				lockMode,
				sf,
				influencers
		);

		final LoadQueryDetails details = Helper.INSTANCE.buildLoadQueryDetails( persister, sf );

		compare( walker, details );
	}
