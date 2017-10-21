	public final SoftLock lockItem(SharedSessionContractImplementor session, Object key, Object version) throws CacheException {
		region().writeLock( key );
		try {
			final Lockable item = (Lockable) region().get( key );
			final long timeout = region().nextTimestamp() + region().getTimeout();
			final Lock lock = (item == null) ? new Lock( timeout, uuid, nextLockId(), version ) : item.lock(
					timeout,
					uuid,
					nextLockId()
			);
			region().put( key, lock );
			return lock;
		}
		finally {
			region().writeUnlock( key );
		}
	}
