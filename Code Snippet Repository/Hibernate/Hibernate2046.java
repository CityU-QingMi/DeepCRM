		@Override
		public void handleSynchronization(EntityPersister persister, Serializable pk, Object entity) {
			if ( !persister.hasNaturalIdentifier() ) {
				// nothing to do
				return;
			}

			persister = locateProperPersister( persister );

			final Object[] naturalIdValuesFromCurrentObjectState = extractNaturalIdValues( entity, persister );
			final boolean changed = ! naturalIdXrefDelegate.sameAsCached(
					persister,
					pk,
					naturalIdValuesFromCurrentObjectState
			);

			if ( changed ) {
				final Object[] cachedNaturalIdValues = naturalIdXrefDelegate.findCachedNaturalId( persister, pk );
				naturalIdXrefDelegate.cacheNaturalIdCrossReference( persister, pk, naturalIdValuesFromCurrentObjectState );
				naturalIdXrefDelegate.stashInvalidNaturalIdReference( persister, cachedNaturalIdValues );

				removeSharedNaturalIdCrossReference(
						persister,
						pk,
						cachedNaturalIdValues
				);
			}
		}
