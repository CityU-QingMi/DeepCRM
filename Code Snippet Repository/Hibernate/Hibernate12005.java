	@Override
	public Object get(SharedSessionContractImplementor session, Object key) throws CacheException {
		LOG.debugf( "Cache[%s] lookup : key[%s]", getName(), key );
		if ( key == null ) {
			return null;
		}
		Object result = cache.get( key );
		if ( result != null ) {
			LOG.debugf( "Cache[%s] hit: %s", getName(), key );
		}
		return result;
	}
