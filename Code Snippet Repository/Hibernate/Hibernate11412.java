	protected void write(SharedSessionContractImplementor session, Object key, Object value) {
		TransactionCoordinator tc = session.getTransactionCoordinator();
		FutureUpdateSynchronization sync = new FutureUpdateSynchronization(tc, asyncWriteCache, requiresTransaction, key, value, region, session.getTimestamp());
		// The update will be invalidating all putFromLoads for the duration of expiration or until removed by the synchronization
		Tombstone tombstone = new Tombstone(sync.getUuid(), region.nextTimestamp() + region.getTombstoneExpiration());
		// The outcome of this operation is actually defined in TombstoneCallInterceptor
		// Metadata in PKVC are cleared and set in the interceptor, too
		writeCache.put(key, tombstone);
		tc.getLocalSynchronizations().registerSynchronization(sync);
	}
