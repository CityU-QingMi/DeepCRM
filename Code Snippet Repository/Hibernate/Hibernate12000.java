	@Override
	public final boolean putFromLoad(
			SharedSessionContractImplementor session,
			Object key,
			Object value,
			long txTimestamp,
			Object version,
			boolean minimalPutOverride)
			throws CacheException {
		try {
			LOG.debugf( "putting key[%s] -> value[%s] into region[%s]", key, value, getInternalRegion().getName() );
			writeLock.lock();
			Lockable item = (Lockable) getInternalRegion().get( session, key );
			boolean writeable = item == null || item.isWriteable( txTimestamp, version, getVersionComparator() );
			if ( writeable ) {
				LOG.debugf(
						"putting key[%s] -> value[%s] into region[%s] success",
						key,
						value,
						getInternalRegion().getName()
				);
				getInternalRegion().put( session, key, new Item( value, version, getInternalRegion().nextTimestamp() ) );
				return true;
			}
			else {
				LOG.debugf(
						"putting key[%s] -> value[%s] into region[%s] fail due to it is unwriteable",
						key,
						value,
						getInternalRegion().getName()
				);
				return false;
			}
		}
		finally {
			writeLock.unlock();
		}
	}
