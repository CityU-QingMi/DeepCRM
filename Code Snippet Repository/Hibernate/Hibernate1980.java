	public Object[] findCachedNaturalId(EntityPersister persister, Serializable pk) {
		persister = locatePersisterForKey( persister );
		final NaturalIdResolutionCache entityNaturalIdResolutionCache = naturalIdResolutionCacheMap.get( persister );
		if ( entityNaturalIdResolutionCache == null ) {
			return null;
		}

		final CachedNaturalId cachedNaturalId = entityNaturalIdResolutionCache.pkToNaturalIdMap.get( pk );
		if ( cachedNaturalId == null ) {
			return null;
		}

		return cachedNaturalId.getValues();
	}
