	protected Serializable loadFromDatasource(final ResolveNaturalIdEvent event) {
		final SessionFactoryImplementor factory = event.getSession().getFactory();
		final boolean stats = factory.getStatistics().isStatisticsEnabled();
		long startTime = 0;
		if ( stats ) {
			startTime = System.nanoTime();
		}
		
		final Serializable pk = event.getEntityPersister().loadEntityIdByNaturalId(
				event.getOrderedNaturalIdValues(),
				event.getLockOptions(),
				event.getSession()
		);
		
		if ( stats ) {
			final NaturalIdRegionAccessStrategy naturalIdCacheAccessStrategy = event.getEntityPersister().getNaturalIdCacheAccessStrategy();
			final String regionName = naturalIdCacheAccessStrategy == null ? null : naturalIdCacheAccessStrategy.getRegion().getName();
			final long endTime = System.nanoTime();
			final long milliseconds = TimeUnit.MILLISECONDS.convert( endTime - startTime, TimeUnit.NANOSECONDS );
			factory.getStatisticsImplementor().naturalIdQueryExecuted(
					regionName,
					milliseconds );
		}
		
		//PK can be null if the entity doesn't exist
		if (pk != null) {
			event.getSession().getPersistenceContext().getNaturalIdHelper().cacheNaturalIdCrossReferenceFromLoad(
					event.getEntityPersister(),
					pk,
					event.getOrderedNaturalIdValues()
			);
		}
		
		return pk;
	}
