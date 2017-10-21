	public LoadQueryDetails makeCollectionLoadQueryDetails(
			CollectionPersister collectionPersister,
			LoadPlan loadPlan,
			QueryBuildingParameters buildingParameters) {
		final CollectionReturn rootReturn = RootHelper.INSTANCE.extractRootReturn( loadPlan, CollectionReturn.class );
		final AliasResolutionContextImpl aliasResolutionContext = new AliasResolutionContextImpl(
				collectionPersister.getFactory()
		);
		return collectionPersister.isOneToMany() ?
				new OneToManyLoadQueryDetails(
						loadPlan,
						aliasResolutionContext,
						rootReturn,
						buildingParameters,
						collectionPersister.getFactory()
				) :
				new BasicCollectionLoadQueryDetails(
						loadPlan,
						aliasResolutionContext,
						rootReturn,
						buildingParameters,
						collectionPersister.getFactory()
				);
	}
