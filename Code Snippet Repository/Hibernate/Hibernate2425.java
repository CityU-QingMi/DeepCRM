	private boolean initializeCollectionFromCache(
			Serializable id,
			CollectionPersister persister,
			PersistentCollection collection,
			SessionImplementor source) {

		if ( !source.getLoadQueryInfluencers().getEnabledFilters().isEmpty()
				&& persister.isAffectedByEnabledFilters( source ) ) {
			LOG.trace( "Disregarding cached version (if any) of collection due to enabled filters" );
			return false;
		}

		final boolean useCache = persister.hasCache() && source.getCacheMode().isGetEnabled();

		if ( !useCache ) {
			return false;
		}

		final SessionFactoryImplementor factory = source.getFactory();
		final CollectionRegionAccessStrategy cacheAccessStrategy = persister.getCacheAccessStrategy();
		final Object ck = cacheAccessStrategy.generateCacheKey( id, persister, factory, source.getTenantIdentifier() );
		final Object ce = CacheHelper.fromSharedCache( source, ck, persister.getCacheAccessStrategy() );

		if ( factory.getStatistics().isStatisticsEnabled() ) {
			if ( ce == null ) {
				factory.getStatisticsImplementor()
						.secondLevelCacheMiss( cacheAccessStrategy.getRegion().getName() );
			}
			else {
				factory.getStatisticsImplementor()
						.secondLevelCacheHit( cacheAccessStrategy.getRegion().getName() );
			}
		}

		if ( ce == null ) {
			return false;
		}

		CollectionCacheEntry cacheEntry = (CollectionCacheEntry) persister.getCacheEntryStructure().destructure(
				ce,
				factory
		);

		final PersistenceContext persistenceContext = source.getPersistenceContext();
		cacheEntry.assemble( collection, persister, persistenceContext.getCollectionOwner( id, persister ) );
		persistenceContext.getCollectionEntry( collection ).postInitialize( collection );
		// addInitializedCollection(collection, persister, id);
		return true;
	}
