	public void registerPendingPut(SharedSessionContractImplementor session, Object key, long txTimestamp) {
		long invalidationTimestamp = this.regionInvalidationTimestamp;
		if (txTimestamp <= invalidationTimestamp) {
			if (trace) {
				log.tracef("registerPendingPut(%s#%s, %d) skipped due to region invalidation (%d)", cache.getName(), key, txTimestamp, invalidationTimestamp);
			}
			return;
		}

		final PendingPut pendingPut = new PendingPut( session );
		final PendingPutMap pendingForKey = new PendingPutMap( pendingPut );

		for (;;) {
			final PendingPutMap existing = pendingPuts.putIfAbsent(key, pendingForKey);
			if (existing != null) {
				if (existing.acquireLock(10, TimeUnit.SECONDS)) {
					try {
						if (existing.isRemoved()) {
							if (trace) {
								log.tracef("Record removed when waiting for the lock.");
							}
							continue;
						}
						if (!existing.hasInvalidator()) {
							existing.put(pendingPut);
						}
					}
					finally {
						existing.releaseLock();
					}
					if (trace) {
						log.tracef("registerPendingPut(%s#%s, %d) ended with %s", cache.getName(), key, txTimestamp, existing);
					}
				}
				else {
					if (trace) {
						log.tracef("registerPendingPut(%s#%s, %d) failed to acquire lock", cache.getName(), key, txTimestamp);
					}
					// Can't get the lock; when we come back we'll be a "naked put"
				}
			}
			else {
				if (trace) {
					log.tracef("registerPendingPut(%s#%s, %d) registered using putIfAbsent: %s", cache.getName(), key, txTimestamp, pendingForKey);
				}
			}
			return;
		}
	}
