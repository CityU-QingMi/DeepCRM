	@Override
	public Map toMap() {
		try {
			final Map<Object, Object> result = new HashMap<Object, Object>();
			for ( Object key : getCache().getKeys() ) {
				result.put( key, getCache().get( key ).getObjectValue() );
			}
			return result;
		}
		catch (Exception e) {
			if ( e instanceof NonStopCacheException ) {
				HibernateNonstopCacheExceptionHandler.getInstance()
						.handleNonstopCacheException( (NonStopCacheException) e );
				return Collections.emptyMap();
			}
			else {
				throw new CacheException( e );
			}
		}
	}
