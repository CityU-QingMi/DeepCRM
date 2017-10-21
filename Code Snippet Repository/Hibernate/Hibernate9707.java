	@Override
	public Object get(SharedSessionContractImplementor session, Object key) throws CacheException {
		try {
			LOG.debugf( "key: %s", key );
			if ( key == null ) {
				return null;
			}
			else {
				final Element element = getCache().get( key );
				if ( element == null ) {
					LOG.debugf( "Element for key %s is null", key );
					return null;
				}
				else {
					return element.getObjectValue();
				}
			}
		}
		catch (net.sf.ehcache.CacheException e) {
			if ( e instanceof NonStopCacheException ) {
				HibernateNonstopCacheExceptionHandler.getInstance()
						.handleNonstopCacheException( (NonStopCacheException) e );
				return null;
			}
			else {
				throw new CacheException( e );
			}
		}
	}
