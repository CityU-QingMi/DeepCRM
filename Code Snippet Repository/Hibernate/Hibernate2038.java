		@Override
		public void cacheNaturalIdCrossReferenceFromLoad(
				EntityPersister persister,
				Serializable id,
				Object[] naturalIdValues) {
			if ( !persister.hasNaturalIdentifier() ) {
				// nothing to do
				return;
			}

			persister = locateProperPersister( persister );

			// 'justAddedLocally' is meant to handle the case where we would get double stats jounaling
			//	from a single load event.  The first put journal would come from the natural id resolution;
			// the second comes from the entity loading.  In this condition, we want to avoid the multiple
			// 'put' stats incrementing.
			final boolean justAddedLocally = naturalIdXrefDelegate.cacheNaturalIdCrossReference( persister, id, naturalIdValues );

			if ( justAddedLocally && persister.hasNaturalIdCache() ) {
				managedSharedCacheEntries( persister, id, naturalIdValues, null, CachedNaturalIdValueSource.LOAD );
			}
		}
