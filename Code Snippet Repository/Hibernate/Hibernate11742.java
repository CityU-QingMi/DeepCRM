	protected void amendCacheConfiguration(String cacheName, ConfigurationBuilder configurationBuilder) {
		if (cacheName.equals(InfinispanRegionFactory.DEF_PENDING_PUTS_RESOURCE)) {
			return;
		}
		if (transactional) {
			if (!cacheName.endsWith("query") && !cacheName.equals(DEF_TIMESTAMPS_RESOURCE) && !cacheName.endsWith(InfinispanRegionFactory.DEF_PENDING_PUTS_RESOURCE)) {
				configurationBuilder.transaction().transactionMode(TransactionMode.TRANSACTIONAL).useSynchronization(true);
			}
		} else {
			configurationBuilder.transaction().transactionMode(TransactionMode.NON_TRANSACTIONAL);
		}
		if (cacheMode != null) {
			if (configurationBuilder.clustering().cacheMode().isInvalidation()) {
				configurationBuilder.clustering().cacheMode(cacheMode);
			}
		}
	}
