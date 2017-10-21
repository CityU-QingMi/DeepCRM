	public void unlockItem(SharedSessionContractImplementor session, Object key, SoftLock lock) throws CacheException {
		while (true) {
			Lockable item = (Lockable) region.get( key );

			if (item != null && item.isUnlockable( lock )) {
				if (region.replace(key, item, ((Lock) item ).unlock(region.nextTimestamp()))) {
					return;
				}
			}
			else {
				handleMissingLock( key, item );
				return;
			}
		}
	}
