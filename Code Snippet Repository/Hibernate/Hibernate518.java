	@Override
	public final void beforeExecutions() throws CacheException {
		// we need to obtain the lock before any actions are executed, since this may be an inverse="true"
		// bidirectional association and it is one of the earlier entity actions which actually updates
		// the database (this action is responsible for second-level cache invalidation only)
		if ( persister.hasCache() ) {
			final CollectionRegionAccessStrategy cache = persister.getCacheAccessStrategy();
			final Object ck = cache.generateCacheKey(
					key,
					persister,
					session.getFactory(),
					session.getTenantIdentifier()
			);
			final SoftLock lock = cache.lockItem( session, ck, null );
			// the old behavior used key as opposed to getKey()
			afterTransactionProcess = new CacheCleanupProcess( key, persister, lock );
		}
	}
