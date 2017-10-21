	@Override
	public boolean insert(SharedSessionContractImplementor session, Object key, Object value) throws CacheException {
		try {
			return actualStrategy.insert( session, key, value );
		}
		catch (NonStopCacheException nonStopCacheException) {
			hibernateNonstopExceptionHandler.handleNonstopCacheException( nonStopCacheException );
			return false;
		}
	}
