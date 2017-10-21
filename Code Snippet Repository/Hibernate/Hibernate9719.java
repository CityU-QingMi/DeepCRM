	public final Object get(SharedSessionContractImplementor session, Object key, long txTimestamp) throws CacheException {
		readLockIfNeeded( key );
		try {
			final Lockable item = (Lockable) region().get( key );

			final boolean readable = item != null && item.isReadable( txTimestamp );
			if ( readable ) {
				return item.getValue();
			}
			else {
				return null;
			}
		}
		finally {
			readUnlockIfNeeded( key );
		}
	}
