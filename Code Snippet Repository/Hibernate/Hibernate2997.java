	@Override
	public CollectionRegionAccessStrategy determineCollectionRegionAccessStrategy(Collection model) {
		final String cacheRegionName = cacheRegionPrefix + model.getCacheRegionName();
		CollectionRegionAccessStrategy accessStrategy = collectionRegionAccessStrategyMap.get( cacheRegionName );
		if ( accessStrategy == null && settings.isSecondLevelCacheEnabled()) {
			final AccessType accessType = AccessType.fromExternalName(model.getCacheConcurrencyStrategy());
			if (accessType != null) {
				LOG.tracev("Building shared cache region for collection data [{0}]", model.getRole());
				CollectionRegion collectionRegion = regionFactory.buildCollectionRegion(
						cacheRegionName,
						sessionFactory.getProperties(),
						CacheDataDescriptionImpl.decode( model)
				);
				accessStrategy = collectionRegion.buildAccessStrategy( accessType );
				collectionRegionAccessStrategyMap.put( cacheRegionName, accessStrategy );
			}
		}
		return accessStrategy;
	}
