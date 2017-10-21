	public LoadPlan buildLoadPlan(
			SessionFactoryImplementor sf,
			OuterJoinLoadable persister,
			LoadQueryInfluencers influencers,
			LockMode lockMode) {
		FetchStyleLoadPlanBuildingAssociationVisitationStrategy strategy = new FetchStyleLoadPlanBuildingAssociationVisitationStrategy(
				sf,
				influencers,
				lockMode
				);
		return MetamodelDrivenLoadPlanBuilder.buildRootEntityLoadPlan( strategy, persister );
	}
