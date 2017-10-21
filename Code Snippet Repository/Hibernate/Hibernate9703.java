	@Override
	public long getSizeInMemory() {
		try {
			return getCache().calculateInMemorySize();
		}
		catch (Throwable t) {
			if ( t instanceof NonStopCacheException ) {
				HibernateNonstopCacheExceptionHandler.getInstance()
						.handleNonstopCacheException( (NonStopCacheException) t );
			}
			return -1;
		}
	}
