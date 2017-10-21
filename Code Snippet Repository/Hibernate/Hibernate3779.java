	protected AbstractLoadQueryDetails(
			LoadPlan loadPlan,
			AliasResolutionContextImpl aliasResolutionContext,
			QueryBuildingParameters buildingParameters,
			String[] keyColumnNames,
			Return rootReturn,
			SessionFactoryImplementor factory) {
		this.keyColumnNames = keyColumnNames;
		this.rootReturn = rootReturn;
		this.loadPlan = loadPlan;
		this.queryProcessor = new LoadQueryJoinAndFetchProcessor( aliasResolutionContext, buildingParameters, factory );
	}
