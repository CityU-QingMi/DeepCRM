	public SoftLock lockItem(SharedSessionContractImplementor session, Object key, Object version) throws CacheException {
		long timeout = region.nextTimestamp() + region.getTimeout();
		while (true) {
			Lockable item = (Lockable) region.get( key );

			if ( item == null ) {
/**/
/**/
/**/
/**/
				Lock lock = new Lock(timeout, uuid, nextLockId(), version);
				if (region.putIfAbsent( key, lock )) {
					return lock;
				}
			}
			else {
				Lock lock = item.lock( timeout, uuid, nextLockId() );
				if (region.replace(key, item, lock)) {
					return lock;
				}
			}
		}
	}
