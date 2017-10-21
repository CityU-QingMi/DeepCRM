	@Override
	@SuppressWarnings("")
	public Object get(SharedSessionContractImplementor session, Object key) throws CacheException {
		Object value = localCache.get( key );

		if ( value == null && checkValid() ) {
			value = cache.get( key );

			if ( value != null ) {
				localCache.put( key, value );
			}
		}
		return value;
	}
