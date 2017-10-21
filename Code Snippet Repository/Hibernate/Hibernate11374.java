	@Override
	protected void invoke(boolean success) {
		// If the region was invalidated during this session, we can't know that the value we're inserting is valid
		// so we'll just null the tombstone
		if (sessionTimestamp < region.getLastRegionInvalidation()) {
			success = false;
		}
		// Exceptions in #afterCompletion() are silently ignored, since the transaction
		// is already committed in DB. However we must not return until we update the cache.
		FutureUpdate futureUpdate = new FutureUpdate(uuid, region.nextTimestamp(), success ? this.value : null);
		for (;;) {
			try {
				cache.put(key, futureUpdate);
				return;
			}
			catch (Exception e) {
				log.failureInAfterCompletion(e);
			}
		}
	}
