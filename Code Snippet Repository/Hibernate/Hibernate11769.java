	@Override
	public boolean afterUpdate(SharedSessionContractImplementor session, Object key, Object value, SoftLock lock)
			throws CacheException {
		while (true) {
			Lockable item = (Lockable) region.get( key );

			if ( item != null && item.isUnlockable( lock ) ) {
				final Lock lockItem = (Lock) item;
				if ( lockItem.wasLockedConcurrently() ) {
					if (region.replace( key, lockItem, lockItem.unlock( region.nextTimestamp() ) )) {
						return false;
					}
				}
				else {
					if (region.replace( key, lockItem, new Item(value, null, region.nextTimestamp(), nextItemId() ))) {
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
