	public <T> T[] getServiceImpls(Class<T> contract) {
		T[] services = (T[]) Array.newInstance( contract, 0 );
		final ServiceTracker serviceTracker = getServiceTracker( contract.getName() );
		try {
			// Yep, this is stupid.  But, it's the only way to prevent #getServices from handing us back Object[].
			services = (T[]) serviceTracker.getServices( services );
			if ( services != null ) {
				return services;
			}
		}
		catch (Exception e) {
			LOG.unableToDiscoverOsgiService( contract.getName(), e );
		}
		return services;
	}
