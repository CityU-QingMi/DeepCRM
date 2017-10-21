	@Override
	public final void unlockItem(SharedSessionContractImplementor session, Object key, SoftLock lock) throws CacheException {

		try {
			LOG.debugf( "unlocking key[%s] in region[%s]", key, getInternalRegion().getName() );
			writeLock.lock();
			Lockable item = (Lockable) getInternalRegion().get( session, key );

			if ( ( item != null ) && item.isUnlockable( lock ) ) {
				decrementLock(session, key, (Lock) item );
			}
			else {
				handleLockExpiry(session, key, item );
			}
		}
		finally {
			writeLock.unlock();
		}
	}
