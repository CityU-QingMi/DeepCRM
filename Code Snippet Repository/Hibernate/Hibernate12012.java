	@Override
	public boolean afterInsert(SharedSessionContractImplementor session, Object key, Object value) throws CacheException {

		try {
			writeLock.lock();
			Lockable item = (Lockable) region.get( session, key );
			if ( item == null ) {
				region.put( session, key, new Item( value, null, region.nextTimestamp() ) );
				return true;
			}
			else {
				return false;
			}
		}
		finally {
			writeLock.unlock();
		}
	}
