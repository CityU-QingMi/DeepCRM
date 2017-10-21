	protected EntityLoadQueryDetails(
			LoadPlan loadPlan,
			String[] keyColumnNames,
			AliasResolutionContextImpl aliasResolutionContext,
			EntityReturn rootReturn,
			QueryBuildingParameters buildingParameters,
			SessionFactoryImplementor factory) {
		super(
				loadPlan,
				aliasResolutionContext,
				buildingParameters,
				keyColumnNames,
				rootReturn,
				factory
		);
		this.entityReferenceAliases = aliasResolutionContext.generateEntityReferenceAliases(
				rootReturn.getQuerySpaceUid(),
				rootReturn.getEntityPersister()
		);
		this.readerCollector = new EntityLoaderReaderCollectorImpl(
				new EntityReturnReader( rootReturn ),
				new EntityReferenceInitializerImpl( rootReturn, entityReferenceAliases, true )
		);
		generate();
	}
