	public boolean endInvalidatingKey(Object lockOwner, Object key, boolean doPFER) {
		PendingPutMap pending = pendingPuts.get(key);
		if (pending == null) {
			if (trace) {
				log.tracef("endInvalidatingKey(%s#%s, %s) could not find pending puts", cache.getName(), key, lockOwnerToString(lockOwner));
			}
			return true;
		}
		if (pending.acquireLock(60, TimeUnit.SECONDS)) {
			try {
				long now = regionFactory.nextTimestamp();
				pending.removeInvalidator(lockOwner, key, now, doPFER);
				// we can't remove the pending put yet because we wait for naked puts
				// pendingPuts should be configured with maxIdle time so won't have memory leak
				return true;
			}
			finally {
				pending.releaseLock();
				if (trace) {
					log.tracef("endInvalidatingKey(%s#%s, %s) ends with %s", cache.getName(), key, lockOwnerToString(lockOwner), pending);
				}
			}
		}
		else {
			if (trace) {
				log.tracef("endInvalidatingKey(%s#%s, %s) failed to acquire lock", cache.getName(), key, lockOwnerToString(lockOwner));
			}
			return false;
		}
	}
