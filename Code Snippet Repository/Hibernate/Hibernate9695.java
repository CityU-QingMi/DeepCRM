	@Override
	public boolean update(SharedSessionContractImplementor session, Object key, Object value) throws CacheException {
		try {
			return actualStrategy.update( session, key, value );
		}
		catch (NonStopCacheException nonStopCacheException) {
			hibernateNonstopExceptionHandler.handleNonstopCacheException( nonStopCacheException );
			return false;
		}
	}
