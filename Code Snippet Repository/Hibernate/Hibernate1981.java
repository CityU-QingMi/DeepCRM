	public Collection<Serializable> getCachedPkResolutions(EntityPersister persister) {
		persister = locatePersisterForKey( persister );

		Collection<Serializable> pks = null;

		final NaturalIdResolutionCache entityNaturalIdResolutionCache = naturalIdResolutionCacheMap.get( persister );
		if ( entityNaturalIdResolutionCache != null ) {
			pks = entityNaturalIdResolutionCache.pkToNaturalIdMap.keySet();
		}

		if ( pks == null || pks.isEmpty() ) {
			return java.util.Collections.emptyList();
		}
		else {
			return java.util.Collections.unmodifiableCollection( pks );
		}
	}
