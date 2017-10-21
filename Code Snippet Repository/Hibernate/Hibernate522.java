		@Override
		public void doAfterTransactionCompletion(boolean success, SharedSessionContractImplementor session) {
			final CollectionRegionAccessStrategy cache = persister.getCacheAccessStrategy();
			final Object ck = cache.generateCacheKey(
					key,
					persister,
					session.getFactory(),
					session.getTenantIdentifier()
			);
			cache.unlockItem( session, ck, lock );
		}
