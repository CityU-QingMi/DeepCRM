	private LoadPlan buildLoadPlan(EntityGraph entityGraph, Mode mode, Class clazz) {

		LoadQueryInfluencers loadQueryInfluencers = new LoadQueryInfluencers( sfi() );
		if ( Mode.FETCH == mode ) {
			loadQueryInfluencers.setFetchGraph( entityGraph );
		}
		else {
			loadQueryInfluencers.setLoadGraph( entityGraph );
		}
		EntityPersister ep = (EntityPersister) sfi().getClassMetadata( clazz );
		AbstractLoadPlanBuildingAssociationVisitationStrategy strategy = Mode.FETCH == mode ? new FetchGraphLoadPlanBuildingStrategy(
				sfi(), loadQueryInfluencers, LockMode.NONE
		) : new LoadGraphLoadPlanBuildingStrategy( sfi(), loadQueryInfluencers, LockMode.NONE );
		return MetamodelDrivenLoadPlanBuilder.buildRootEntityLoadPlan( strategy, ep );
	}
