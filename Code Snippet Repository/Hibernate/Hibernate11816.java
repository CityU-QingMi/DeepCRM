	public <T> T getServiceImpl(Class<T> contract) {
		final ServiceTracker serviceTracker = getServiceTracker( contract.getName() );
		try {
			return (T) serviceTracker.waitForService( 1000 );
		}
		catch (Exception e) {
			LOG.unableToDiscoverOsgiService( contract.getName(), e );
			return null;
		}
	}
