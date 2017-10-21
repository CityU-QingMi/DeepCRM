	@Override
	public EntityRegionAccessStrategy determineEntityRegionAccessStrategy(PersistentClass model) {
		final String cacheRegionName = cacheRegionPrefix + model.getRootClass().getCacheRegionName();
		EntityRegionAccessStrategy accessStrategy = entityRegionAccessStrategyMap.get( cacheRegionName );
		if ( accessStrategy == null && settings.isSecondLevelCacheEnabled() ) {
			final AccessType accessType = AccessType.fromExternalName( model.getCacheConcurrencyStrategy() );
			if ( accessType != null ) {
				LOG.tracef( "Building shared cache region for entity data [%s]", model.getEntityName() );
				EntityRegion entityRegion = regionFactory.buildEntityRegion(
						cacheRegionName,
						sessionFactory.getProperties(),
						CacheDataDescriptionImpl.decode( model )
				);
				accessStrategy = entityRegion.buildAccessStrategy( accessType );
				entityRegionAccessStrategyMap.put( cacheRegionName, accessStrategy );
			}
		}
		return accessStrategy;
	}
