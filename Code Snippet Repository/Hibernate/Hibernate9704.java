	@Override
	public long getElementCountInMemory() {
		try {
			return getCache().getMemoryStoreSize();
		}
		catch (net.sf.ehcache.CacheException ce) {
			if ( ce instanceof NonStopCacheException ) {
				HibernateNonstopCacheExceptionHandler.getInstance()
						.handleNonstopCacheException( (NonStopCacheException) ce );
				return -1;
			}
			else {
				throw new CacheException( ce );
			}
		}
	}
