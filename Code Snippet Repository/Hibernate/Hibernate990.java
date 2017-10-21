	@Override
	@SuppressWarnings("")
	public <S> Collection<S> loadJavaServices(Class<S> serviceContract) {
		ServiceLoader<S> serviceLoader = serviceLoaders.get( serviceContract );
		if ( serviceLoader == null ) {
			serviceLoader = ServiceLoader.load( serviceContract, getAggregatedClassLoader() );
			serviceLoaders.put( serviceContract, serviceLoader );
		}
		final LinkedHashSet<S> services = new LinkedHashSet<S>();
		for ( S service : serviceLoader ) {
			services.add( service );
		}
		return services;
	}
