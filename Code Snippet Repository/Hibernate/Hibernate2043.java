		@Override
		public void removeSharedNaturalIdCrossReference(EntityPersister persister, Serializable id, Object[] naturalIdValues) {
			if ( !persister.hasNaturalIdentifier() ) {
				// nothing to do
				return;
			}

			if ( ! persister.hasNaturalIdCache() ) {
				// nothing to do
				return;
			}

			// todo : couple of things wrong here:
			//		1) should be using access strategy, not plain evict..
			//		2) should prefer session-cached values if any (requires interaction from removeLocalNaturalIdCrossReference

			persister = locateProperPersister( persister );
			final NaturalIdRegionAccessStrategy naturalIdCacheAccessStrategy = persister.getNaturalIdCacheAccessStrategy();
			final Object naturalIdCacheKey = naturalIdCacheAccessStrategy.generateCacheKey( naturalIdValues, persister, session );
			naturalIdCacheAccessStrategy.evict( naturalIdCacheKey );

//			if ( sessionCachedNaturalIdValues != null
//					&& !Arrays.equals( sessionCachedNaturalIdValues, deletedNaturalIdValues ) ) {
//				final NaturalIdCacheKey sessionNaturalIdCacheKey = new NaturalIdCacheKey( sessionCachedNaturalIdValues, persister, session );
//				naturalIdCacheAccessStrategy.evict( sessionNaturalIdCacheKey );
//			}
		}
