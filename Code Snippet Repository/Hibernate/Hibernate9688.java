	@Override
	public CollectionRegionAccessStrategy createCollectionRegionAccessStrategy(
			EhcacheCollectionRegion collectionRegion,
			AccessType accessType) {
		return new NonstopAwareCollectionRegionAccessStrategy(
				actualFactory.createCollectionRegionAccessStrategy(
						collectionRegion,
						accessType
				), HibernateNonstopCacheExceptionHandler.getInstance()
		);
	}
