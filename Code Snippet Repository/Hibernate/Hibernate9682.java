	@Override
	public void stop() {
		try {
			if ( manager != null ) {
				mbeanRegistrationHelper.unregisterMBean();
				manager.shutdown();
				manager = null;
			}
		}
		catch (net.sf.ehcache.CacheException e) {
			throw new CacheException( e );
		}
	}
