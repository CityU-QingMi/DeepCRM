	public void stashInvalidNaturalIdReference(EntityPersister persister, Object[] invalidNaturalIdValues) {
		persister = locatePersisterForKey( persister );

		final NaturalIdResolutionCache entityNaturalIdResolutionCache = naturalIdResolutionCacheMap.get( persister );
		if ( entityNaturalIdResolutionCache == null ) {
			throw new AssertionFailure( "Expecting NaturalIdResolutionCache to exist already for entity " + persister.getEntityName() );
		}

		entityNaturalIdResolutionCache.stashInvalidNaturalIdReference( invalidNaturalIdValues );
	}
