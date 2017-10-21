		@Override
		public void manageLocalNaturalIdCrossReference(
				EntityPersister persister,
				Serializable id,
				Object[] state,
				Object[] previousState,
				CachedNaturalIdValueSource source) {
			if ( !persister.hasNaturalIdentifier() ) {
				// nothing to do
				return;
			}

			persister = locateProperPersister( persister );
			final Object[] naturalIdValues = extractNaturalIdValues( state, persister );

			// cache
			naturalIdXrefDelegate.cacheNaturalIdCrossReference( persister, id, naturalIdValues );
		}
