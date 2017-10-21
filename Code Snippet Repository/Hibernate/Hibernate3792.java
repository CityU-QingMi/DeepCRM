	public LoadQueryDetails makeEntityLoadQueryDetails(
			LoadPlan loadPlan,
			String[] keyColumnNames,
			QueryBuildingParameters buildingParameters,
			SessionFactoryImplementor factory) {

		// TODO: how should shouldUseOptionalEntityInformation be used?
		// final int batchSize = buildingParameters.getBatchSize();
		// final boolean shouldUseOptionalEntityInformation = batchSize == 1;

		final EntityReturn rootReturn = RootHelper.INSTANCE.extractRootReturn( loadPlan, EntityReturn.class );
		final String[] keyColumnNamesToUse = keyColumnNames != null
				? keyColumnNames
				: ( (Queryable) rootReturn.getEntityPersister() ).getIdentifierColumnNames();
		// Should be just one querySpace (of type EntityQuerySpace) in querySpaces.  Should we validate that?
		// Should we make it a util method on Helper like we do for extractRootReturn ?
		final AliasResolutionContextImpl aliasResolutionContext = new AliasResolutionContextImpl( factory );
		return new EntityLoadQueryDetails(
				loadPlan,
				keyColumnNamesToUse,
				aliasResolutionContext,
				rootReturn,
				buildingParameters,
				factory
		);
	}
