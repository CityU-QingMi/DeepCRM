	@Override
	public final Object get(SharedSessionContractImplementor session, Object key, long txTimestamp) throws CacheException {
		LOG.debugf( "getting key[%s] from region[%s]", key, getInternalRegion().getName() );
		try {
			readLock.lock();
			Lockable item = (Lockable) getInternalRegion().get( session, key );

			boolean readable = item != null && item.isReadable( txTimestamp );
			if ( readable ) {
				LOG.debugf( "hit key[%s] in region[%s]", key, getInternalRegion().getName() );
				return item.getValue();
			}
			else {
				if ( item == null ) {
					LOG.debugf( "miss key[%s] in region[%s]", key, getInternalRegion().getName() );
				}
				else {
					LOG.debugf( "hit key[%s] in region[%s], but it is unreadable", key, getInternalRegion().getName() );
				}
				return null;
			}
		}
		finally {
			readLock.unlock();
		}
	}
