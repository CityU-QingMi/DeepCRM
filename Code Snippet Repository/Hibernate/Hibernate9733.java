	@Override
	public boolean insert(SharedSessionContractImplementor session, Object key, Object value, Object version)
			throws CacheException {
		//OptimisticCache? versioning?
		try {
			ehcache.put( new Element( key, value ) );
			return true;
		}
		catch (net.sf.ehcache.CacheException e) {
			throw new CacheException( e );
		}
	}
