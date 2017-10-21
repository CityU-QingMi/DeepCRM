	public boolean beginInvalidatingRegion() {
		if (trace) {
			log.trace("Started invalidating region " + cache.getName());
		}
		boolean ok = true;
		long now = regionFactory.nextTimestamp();
		// deny all puts until endInvalidatingRegion is called; at that time the region should be already
		// in INVALID state, therefore all new requests should be blocked and ongoing should fail by timestamp
		synchronized (this) {
			regionInvalidationTimestamp = Long.MAX_VALUE;
			regionInvalidations++;
		}

		try {
			// Acquire the lock for each entry to ensure any ongoing
			// work associated with it is completed before we return
			// We cannot erase the map: if there was ongoing invalidation and we removed it, registerPendingPut
			// started after that would have no way of finding out that the entity *is* invalidated (it was
			// removed from the cache and now the DB is about to be updated).
			for (Iterator<PendingPutMap> it = pendingPuts.values().iterator(); it.hasNext(); ) {
				PendingPutMap entry = it.next();
				if (entry.acquireLock(60, TimeUnit.SECONDS)) {
					try {
						entry.invalidate(now);
					}
					finally {
						entry.releaseLock();
					}
				}
				else {
					ok = false;
				}
			}
		}
		catch (Exception e) {
			ok = false;
		}
		return ok;
	}
