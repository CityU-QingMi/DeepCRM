	public final void unlockItem(SharedSessionContractImplementor session, Object key, SoftLock lock) throws CacheException {
		region().writeLock( key );
		try {
			final Lockable item = (Lockable) region().get( key );

			if ( (item != null) && item.isUnlockable( lock ) ) {
				decrementLock( key, (Lock) item );
			}
			else {
				handleLockExpiry( key, item );
			}
		}
		finally {
			region().writeUnlock( key );
		}
	}
