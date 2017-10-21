	@Override
	public boolean update(SharedSessionContractImplementor session, Object key, Object value) throws CacheException {
		try {
			ehcache.put( new Element( key, value ) );
			return true;
		}
		catch (net.sf.ehcache.CacheException e) {
			throw new CacheException( e );
		}
	}
