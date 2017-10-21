	@Override
	public NaturalIdRegionAccessStrategy determineNaturalIdRegionAccessStrategy(PersistentClass model) {
		NaturalIdRegionAccessStrategy naturalIdAccessStrategy = null;
		if ( model.hasNaturalId() && model.getNaturalIdCacheRegionName() != null ) {
			final String naturalIdCacheRegionName = cacheRegionPrefix + model.getNaturalIdCacheRegionName();
			naturalIdAccessStrategy = naturalIdRegionAccessStrategyMap.get( naturalIdCacheRegionName );

			if ( naturalIdAccessStrategy == null && settings.isSecondLevelCacheEnabled() ) {
				final CacheDataDescriptionImpl cacheDataDescription = CacheDataDescriptionImpl.decode( model );

				NaturalIdRegion naturalIdRegion = null;
				try {
					naturalIdRegion = regionFactory.buildNaturalIdRegion(
							naturalIdCacheRegionName,
							sessionFactory.getProperties(),
							cacheDataDescription
					);
				}
				catch ( UnsupportedOperationException e ) {
					LOG.warnf(
							"Shared cache region factory [%s] does not support natural id caching; " +
									"shared NaturalId caching will be disabled for not be enabled for %s",
							regionFactory.getClass().getName(),
							model.getEntityName()
					);
				}

				if (naturalIdRegion != null) {
					naturalIdAccessStrategy = naturalIdRegion.buildAccessStrategy( regionFactory.getDefaultAccessType() );
					naturalIdRegionAccessStrategyMap.put( naturalIdCacheRegionName, naturalIdAccessStrategy );
				}
			}
		}
		return naturalIdAccessStrategy;
	}
