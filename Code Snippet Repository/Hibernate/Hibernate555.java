	@Override
	public void doAfterTransactionCompletion(boolean success, SharedSessionContractImplementor session) throws HibernateException {
		final EntityPersister persister = getPersister();
		if ( success && isCachePutEnabled( persister, getSession() ) ) {
			final EntityRegionAccessStrategy cache = persister.getCacheAccessStrategy();
			SessionFactoryImplementor sessionFactoryImplementor = session.getFactory();
			final Object ck = cache.generateCacheKey( getId(), persister, sessionFactoryImplementor, session.getTenantIdentifier() );
			final boolean put = cacheAfterInsert( cache, ck );

			if ( put && sessionFactoryImplementor.getStatistics().isStatisticsEnabled() ) {
				sessionFactoryImplementor.getStatisticsImplementor()
						.secondLevelCachePut( cache.getRegion().getName() );
			}
		}
		postCommitInsert( success );
	}
