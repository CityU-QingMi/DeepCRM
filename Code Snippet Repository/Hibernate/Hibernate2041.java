		@Override
		public void manageSharedNaturalIdCrossReference(
				EntityPersister persister,
				final Serializable id,
				Object[] state,
				Object[] previousState,
				CachedNaturalIdValueSource source) {
			if ( !persister.hasNaturalIdentifier() ) {
				// nothing to do
				return;
			}

			if ( !persister.hasNaturalIdCache() ) {
				// nothing to do
				return;
			}

			persister = locateProperPersister( persister );
			final Object[] naturalIdValues = extractNaturalIdValues( state, persister );
			final Object[] previousNaturalIdValues = previousState == null ? null : extractNaturalIdValues( previousState, persister );

			managedSharedCacheEntries( persister, id, naturalIdValues, previousNaturalIdValues, source );
		}
