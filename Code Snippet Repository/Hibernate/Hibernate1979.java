	public Object[] removeNaturalIdCrossReference(EntityPersister persister, Serializable pk, Object[] naturalIdValues) {
		persister = locatePersisterForKey( persister );
		validateNaturalId( persister, naturalIdValues );

		final NaturalIdResolutionCache entityNaturalIdResolutionCache = naturalIdResolutionCacheMap.get( persister );
		Object[] sessionCachedNaturalIdValues = null;
		if ( entityNaturalIdResolutionCache != null ) {
			final CachedNaturalId cachedNaturalId = entityNaturalIdResolutionCache.pkToNaturalIdMap
					.remove( pk );
			if ( cachedNaturalId != null ) {
				entityNaturalIdResolutionCache.naturalIdToPkMap.remove( cachedNaturalId );
				sessionCachedNaturalIdValues = cachedNaturalId.getValues();
			}
		}

		if ( persister.hasNaturalIdCache() ) {
			final NaturalIdRegionAccessStrategy naturalIdCacheAccessStrategy = persister
					.getNaturalIdCacheAccessStrategy();
			final Object naturalIdCacheKey = naturalIdCacheAccessStrategy.generateCacheKey( naturalIdValues, persister, session() );
			naturalIdCacheAccessStrategy.evict( naturalIdCacheKey );

			if ( sessionCachedNaturalIdValues != null
					&& !Arrays.equals( sessionCachedNaturalIdValues, naturalIdValues ) ) {
				final Object sessionNaturalIdCacheKey = naturalIdCacheAccessStrategy.generateCacheKey( sessionCachedNaturalIdValues, persister, session() );
				naturalIdCacheAccessStrategy.evict( sessionNaturalIdCacheKey );
			}
		}

		return sessionCachedNaturalIdValues;
	}
