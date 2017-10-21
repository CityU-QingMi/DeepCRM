	@Override
	public boolean afterUpdate(SharedSessionContractImplementor session, Object key, Object value, Object currentVersion, Object previousVersion, SoftLock lock)
			throws CacheException {
		while (true) {
			Lockable item = (Lockable) region.get( key );

			if ( item != null && item.isUnlockable( lock ) ) {
				Lock lockItem = (Lock) item;
				if ( lockItem.wasLockedConcurrently() ) {
					if (region.replace( key, lockItem, lockItem.unlock( region.nextTimestamp() ))) {
						return false;
					}
				}
				else {
					if (region.replace( key, lockItem, new Item(value, currentVersion, region.nextTimestamp(), nextItemId() ))) {
						return true;
					}
				}
			}
			else {
				handleMissingLock( key, item );
				return false;
			}

		}
	}
