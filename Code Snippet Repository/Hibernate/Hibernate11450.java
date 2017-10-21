	private void removeEntries(boolean inTransaction, KeyValueFilter filter) {
		// If the transaction is required, we simply need it -> will create our own
		boolean startedTx = false;
		if ( !inTransaction && requiresTransaction) {
			try {
				tm.begin();
				startedTx = true;
			}
			catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		// We can never use cache.clear() since tombstones must be kept.
		try {
			AdvancedCache localCache = Caches.localCache(cache);
			CloseableIterator<CacheEntry> it = Caches.entrySet(localCache, Tombstone.EXCLUDE_TOMBSTONES).iterator();
			long now = nextTimestamp();
			try {
				while (it.hasNext()) {
					// Cannot use it.next(); it.remove() due to ISPN-5653
					CacheEntry entry = it.next();
					switch (strategy) {
						case TOMBSTONES:
							localCache.remove(entry.getKey(), entry.getValue());
							break;
						case VERSIONED_ENTRIES:
							localCache.put(entry.getKey(), new VersionedEntry(null, null, now), tombstoneExpiration, TimeUnit.MILLISECONDS);
							break;
					}
				}
			}
			finally {
				it.close();
			}
		}
		finally {
			if (startedTx) {
				try {
					tm.commit();
				}
				catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		}
	}
