	@Override
	public void destroy() throws CacheException {
		try {
			getCache().getCacheManager().removeCache( getCache().getName() );
		}
		catch (IllegalStateException e) {
			//When Spring and Hibernate are both involved this will happen in normal shutdown operation.
			//Do not throw an exception, simply log this one.
			LOG.debug( "This can happen if multiple frameworks both try to shutdown ehcache", e );
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
