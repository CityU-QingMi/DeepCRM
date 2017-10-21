	@Override
	public final SoftLock lockItem(SharedSessionContractImplementor session, Object key, Object version) throws CacheException {

		try {
			LOG.debugf( "locking key[%s] in region[%s]", key, getInternalRegion().getName() );
			writeLock.lock();
			Lockable item = (Lockable) getInternalRegion().get( session, key );
			long timeout = getInternalRegion().nextTimestamp() + getInternalRegion().getTimeout();
			final Lock lock = ( item == null ) ? new Lock( timeout, uuid, nextLockId(), version ) : item.lock(
					timeout,
					uuid,
					nextLockId()
			);
			getInternalRegion().put( session, key, lock );
			return lock;
		}
		finally {
			writeLock.unlock();
		}
	}
