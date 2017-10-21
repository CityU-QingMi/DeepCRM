	public final void writeLock(Object key) throws CacheException {
		try {
			lockProvider.getSyncForKey( key ).lock( LockType.WRITE );
		}
		catch (net.sf.ehcache.CacheException e) {
			if ( e instanceof NonStopCacheException ) {
				HibernateNonstopCacheExceptionHandler.getInstance()
						.handleNonstopCacheException( (NonStopCacheException) e );
			}
			else {
				throw new CacheException( e );
			}
		}
	}
