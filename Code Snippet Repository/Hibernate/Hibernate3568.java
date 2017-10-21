	public CollectionLoader(
			QueryableCollection collectionPersister,
			QueryBuildingParameters buildingParameters) {
		super( collectionPersister, buildingParameters );
		if ( log.isDebugEnabled() ) {
			log.debugf(
					"Static select for collection %s: %s",
					collectionPersister.getRole(),
					getStaticLoadQuery().getSqlStatement()
			);
		}
	}
