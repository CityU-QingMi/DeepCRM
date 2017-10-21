	@Override
	public void remove(SharedSessionContractImplementor session, Object key) throws CacheException {
		// there's no 'afterRemove', so we have to use our own synchronization
		// the API does not provide version of removed item but we can't load it from the cache
		// as that would be prone to race conditions - if the entry was updated in the meantime
		// the remove could be discarded and we would end up with stale record
		// See VersionedTest#testCollectionUpdate for such situation
		TransactionCoordinator transactionCoordinator = session.getTransactionCoordinator();
		RemovalSynchronization sync = new RemovalSynchronization(transactionCoordinator, writeCache, false, region, key);
		transactionCoordinator.getLocalSynchronizations().registerSynchronization(sync);
	}
