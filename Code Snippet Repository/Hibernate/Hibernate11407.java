	public boolean beginInvalidatingWithPFER(Object lockOwner, Object key, Object valueForPFER) {
		for (;;) {
			PendingPutMap pending = new PendingPutMap(null);
			PendingPutMap prev = pendingPuts.putIfAbsent(key, pending);
			if (prev != null) {
				pending = prev;
			}
			if (pending.acquireLock(60, TimeUnit.SECONDS)) {
				try {
					if (pending.isRemoved()) {
						if (trace) {
							log.tracef("Record removed when waiting for the lock.");
						}
						continue;
					}
					long now = regionFactory.nextTimestamp();
					pending.invalidate(now);
					pending.addInvalidator(lockOwner, valueForPFER, now);
				}
				finally {
					pending.releaseLock();
				}
				if (trace) {
					log.tracef("beginInvalidatingKey(%s#%s, %s) ends with %s", cache.getName(), key, lockOwnerToString(lockOwner), pending);
				}
				return true;
			}
			else {
				log.tracef("beginInvalidatingKey(%s#%s, %s) failed to acquire lock", cache.getName(), key, lockOwnerToString(lockOwner));
				return false;
			}
		}
	}
