	private Object getFromSharedCache(
		final LoadEvent event,
		final EntityPersister persister,
		SessionImplementor source ) {
		final EntityRegionAccessStrategy cache = persister.getCacheAccessStrategy();
		final Object ck = cache.generateCacheKey(
				event.getEntityId(),
				persister,
				source.getFactory(),
				source.getTenantIdentifier()
		);

		final Object ce = CacheHelper.fromSharedCache( source, ck, persister.getCacheAccessStrategy() );
		if ( source.getFactory().getStatistics().isStatisticsEnabled() ) {
			if ( ce == null ) {
				source.getFactory().getStatisticsImplementor().secondLevelCacheMiss(
						cache.getRegion().getName()
				);
			}
			else {
				source.getFactory().getStatisticsImplementor().secondLevelCacheHit(
						cache.getRegion().getName()
				);
			}
		}
		return ce;
	}
