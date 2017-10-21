	@Override
	public synchronized void dispose() throws CacheException {
		if ( status == Status.STATUS_SHUTDOWN ) {
			return;
		}

		try {
			getMBeanServer().unregisterMBean( cacheManagerObjectName );
		}
		catch (Exception e) {
			LOG.warn(
					"Error unregistering object instance " + cacheManagerObjectName + " . Error was " + e.getMessage(),
					e
			);
		}
		ehcacheHibernate = null;
		cacheManagerObjectName = null;
		status = Status.STATUS_SHUTDOWN;
	}
