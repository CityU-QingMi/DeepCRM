	@Override
	@SuppressWarnings("")
	public void put(SharedSessionContractImplementor session, final Object key, final Object value) throws CacheException {
		try {
			// We ensure ASYNC semantics (JBCACHE-1175) and make sure previous
			// value is not loaded from cache store cos it's not needed.
			timestampsPutCache.put( key, value );
		}
		catch (Exception e) {
			throw new CacheException( e );
		}
	}
