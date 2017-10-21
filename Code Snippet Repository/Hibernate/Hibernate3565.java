	public AbstractLoadPlanBasedCollectionInitializer(
			QueryableCollection collectionPersister,
			QueryBuildingParameters buildingParameters) {
		super( collectionPersister.getFactory() );
		this.collectionPersister = collectionPersister;
		this.lockOptions = buildingParameters.getLockMode() != null
				? new LockOptions( buildingParameters.getLockMode() )
				: buildingParameters.getLockOptions();

		final FetchStyleLoadPlanBuildingAssociationVisitationStrategy strategy =
				new FetchStyleLoadPlanBuildingAssociationVisitationStrategy(
						collectionPersister.getFactory(),
						buildingParameters.getQueryInfluencers(),
						this.lockOptions.getLockMode()
		);

		final LoadPlan plan = MetamodelDrivenLoadPlanBuilder.buildRootCollectionLoadPlan( strategy, collectionPersister );
		this.staticLoadQuery = BatchingLoadQueryDetailsFactory.INSTANCE.makeCollectionLoadQueryDetails(
				collectionPersister,
				plan,
				buildingParameters
		);
	}
