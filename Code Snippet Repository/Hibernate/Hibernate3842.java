	public ResultSetProcessorImpl(
			LoadPlan loadPlan,
			AliasResolutionContext aliasResolutionContext,
			RowReader rowReader,
			boolean shouldUseOptionalEntityInstance,
			boolean hadSubselectFetches) {
		this.loadPlan = loadPlan;
		this.aliasResolutionContext = aliasResolutionContext;
		this.rowReader = rowReader;
		this.shouldUseOptionalEntityInstance = shouldUseOptionalEntityInstance;
		this.hadSubselectFetches = hadSubselectFetches;
	}
