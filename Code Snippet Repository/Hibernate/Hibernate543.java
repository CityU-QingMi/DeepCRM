	@Override
	public void doAfterTransactionCompletion(boolean success, SharedSessionContractImplementor session) throws HibernateException {
		EntityPersister entityPersister = getPersister();
		if ( entityPersister.hasCache() ) {
			EntityRegionAccessStrategy cache = entityPersister.getCacheAccessStrategy();
			final Object ck = cache.generateCacheKey(
					getId(),
					entityPersister,
					session.getFactory(),
					session.getTenantIdentifier()
			);
			cache.unlockItem( session, ck, lock );
		}
		postCommitDelete( success );
	}
