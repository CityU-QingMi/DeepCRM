	protected AbstractCollectionLoadQueryDetails(
			LoadPlan loadPlan,
			AliasResolutionContextImpl aliasResolutionContext,
			CollectionReturn rootReturn,
			QueryBuildingParameters buildingParameters,
			SessionFactoryImplementor factory) {
		super(
				loadPlan,
				aliasResolutionContext,
				buildingParameters,
				( (QueryableCollection) rootReturn.getCollectionPersister() ).getKeyColumnNames(),
				rootReturn,
				factory
		);
		final String elementUid = rootReturn.getCollectionPersister().getElementType().isEntityType() ?
				rootReturn.getElementGraph().getQuerySpaceUid() :
				null;

		this.collectionReferenceAliases = aliasResolutionContext.generateCollectionReferenceAliases(
				rootReturn.getQuerySpaceUid(),
				rootReturn.getCollectionPersister(),
				elementUid
		);
		this.readerCollector = new CollectionLoaderReaderCollectorImpl(
				new CollectionReturnReader( rootReturn ),
				new CollectionReferenceInitializerImpl( rootReturn, collectionReferenceAliases )
		);
		if ( rootReturn.allowElementJoin() && rootReturn.getCollectionPersister().getElementType().isEntityType() ) {
			final EntityReference elementEntityReference = rootReturn.getElementGraph().resolveEntityReference();
			readerCollector.add(
				new EntityReferenceInitializerImpl( elementEntityReference, collectionReferenceAliases.getEntityElementAliases() )
			);
		}
		if ( rootReturn.allowIndexJoin() && rootReturn.getCollectionPersister().getIndexType().isEntityType() ) {
			final EntityReference indexEntityReference = rootReturn.getIndexGraph().resolveEntityReference();
			final EntityReferenceAliases indexEntityReferenceAliases = aliasResolutionContext.generateEntityReferenceAliases(
					indexEntityReference.getQuerySpaceUid(),
					indexEntityReference.getEntityPersister()
			);
			readerCollector.add(
					new EntityReferenceInitializerImpl( indexEntityReference, indexEntityReferenceAliases )
			);
		}
	}
