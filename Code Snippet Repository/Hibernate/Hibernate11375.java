	@Override
	@SuppressWarnings("")
	public Object get(SharedSessionContractImplementor session, Object key, long txTimestamp) throws CacheException {
		if ( !region.checkValid() ) {
			return null;
		}
		final Object val = cache.get( key );
		if ( val == null ) {
			putValidator.registerPendingPut(session, key, txTimestamp );
		}
		return val;
	}
