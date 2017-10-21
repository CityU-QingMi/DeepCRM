	@Override
	public Object get(SharedSessionContractImplementor session, Object key) throws CacheException {
		if ( !checkValid() ) {
			return null;
		}

		// In Infinispan get doesn't acquire any locks, so no need to suspend the tx.
		// In the past, when get operations acquired locks, suspending the tx was a way
		// to avoid holding locks that would prevent updates.
		// Add a zero (or low) timeout option so we don't block
		// waiting for tx's that did a put to commit
		Object result = null;
		Map map = transactionContext.get(session);
		if (map != null) {
			result = map.get(key);
		}
		if (result == null) {
			result = getCache.get( key );
		}
		return result;
	}
