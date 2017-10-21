	@After
	public void tearDown() throws Exception {
		cleanup.forEach(Runnable::run);
		cleanup.clear();
		try {
			DualNodeJtaTransactionManagerImpl.cleanupTransactions();
		}
		finally {
			DualNodeJtaTransactionManagerImpl.cleanupTransactionManagers();
		}
		cache.clear();
		cm.getCache(cache.getName() + "-" + InfinispanRegionFactory.DEF_PENDING_PUTS_RESOURCE).clear();
	}
