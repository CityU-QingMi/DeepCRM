	public boolean cacheNaturalIdCrossReference(EntityPersister persister, Serializable pk, Object[] naturalIdValues) {
		validateNaturalId( persister, naturalIdValues );

		NaturalIdResolutionCache entityNaturalIdResolutionCache = naturalIdResolutionCacheMap.get( persister );
		if ( entityNaturalIdResolutionCache == null ) {
			entityNaturalIdResolutionCache = new NaturalIdResolutionCache( persister );
			NaturalIdResolutionCache previousInstance = naturalIdResolutionCacheMap.putIfAbsent( persister, entityNaturalIdResolutionCache );
			if ( previousInstance != null ) {
				entityNaturalIdResolutionCache = previousInstance;
			}
		}
		return entityNaturalIdResolutionCache.cache( pk, naturalIdValues );
	}
