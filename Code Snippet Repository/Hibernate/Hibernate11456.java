	@Override
	@SuppressWarnings("")
	public void put(SharedSessionContractImplementor session, Object key, Object value) throws CacheException {
		if ( checkValid() ) {
			// See HHH-7898: Even with FAIL_SILENTLY flag, failure to write in transaction
			// fails the whole transaction. It is an Infinispan quirk that cannot be fixed
			// ISPN-5356 tracks that. This is because if the transaction continued the
			// value could be committed on backup owners, including the failed operation,
			// and the result would not be consistent.
			TransactionCoordinator tc = session.getTransactionCoordinator();
			if (tc != null && tc.isJoined()) {
				tc.getLocalSynchronizations().registerSynchronization(new PostTransactionQueryUpdate(tc, session, key, value));
				// no need to synchronize as the transaction will be accessed by only one thread
				Map map = transactionContext.get(session);
				if (map == null) {
					transactionContext.put(session, map = new HashMap());
				}
				map.put(key, value);
				return;
			}
			// Here we don't want to suspend the tx. If we do:
			// 1) We might be caching query results that reflect uncommitted
			// changes. No tx == no WL on cache node, so other threads
			// can prematurely see those query results
			// 2) No tx == immediate replication. More overhead, plus we
			// spread issue #1 above around the cluster

			// Add a zero (or quite low) timeout option so we don't block.
			// Ignore any TimeoutException. Basically we forego caching the
			// query result in order to avoid blocking.
			// Reads are done with suspended tx, so they should not hold the
			// lock for long.  Not caching the query result is OK, since
			// any subsequent read will just see the old result with its
			// out-of-date timestamp; that result will be discarded and the
			// db query performed again.
			putCache.put( key, value );
		}
	}
