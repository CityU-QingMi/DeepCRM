	public QueryBuildingParametersImpl(
			LoadQueryInfluencers loadQueryInfluencers,
			int batchSize,
			LockMode lockMode,
			LockOptions lockOptions) {
		this.loadQueryInfluencers = loadQueryInfluencers;
		this.batchSize = batchSize;
		this.lockMode = lockMode;
		this.lockOptions = lockOptions;
	}
