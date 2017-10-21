	@Override
	public void put(SharedSessionContractImplementor session, Object key, Object value) throws CacheException {
		LOG.debugf( "key: %s value: %s", key, value );
		try {
			final Element element = new Element( key, value );
			getCache().put( element );
		}
		catch (IllegalArgumentException e) {
			throw new CacheException( e );
		}
		catch (IllegalStateException e) {
			throw new CacheException( e );
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
