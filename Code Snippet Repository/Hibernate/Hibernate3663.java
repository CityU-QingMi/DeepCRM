	public AbstractLoadPlanBasedEntityLoader(
			OuterJoinLoadable entityPersister,
			SessionFactoryImplementor factory,
			String[] uniqueKeyColumnNames,
			Type uniqueKeyType,
			QueryBuildingParameters buildingParameters) {
		super( factory );
		this.entityPersister = entityPersister;
		this.uniqueKeyType = uniqueKeyType;
		this.entityName = entityPersister.getEntityName();

		final LoadPlanBuildingAssociationVisitationStrategy strategy;
		if ( buildingParameters.getQueryInfluencers().getFetchGraph() != null ) {
			strategy = new FetchGraphLoadPlanBuildingStrategy(
					factory, buildingParameters.getQueryInfluencers(),buildingParameters.getLockMode()
			);
		}
		else if ( buildingParameters.getQueryInfluencers().getLoadGraph() != null ) {
			strategy = new LoadGraphLoadPlanBuildingStrategy(
					factory, buildingParameters.getQueryInfluencers(),buildingParameters.getLockMode()
			);
		}
		else {
			strategy = new FetchStyleLoadPlanBuildingAssociationVisitationStrategy(
					factory, buildingParameters.getQueryInfluencers(),buildingParameters.getLockMode()
			);
		}

		final LoadPlan plan = MetamodelDrivenLoadPlanBuilder.buildRootEntityLoadPlan( strategy, entityPersister );
		this.staticLoadQuery = BatchingLoadQueryDetailsFactory.INSTANCE.makeEntityLoadQueryDetails(
				plan,
				uniqueKeyColumnNames,
				buildingParameters,
				factory
		);
	}
