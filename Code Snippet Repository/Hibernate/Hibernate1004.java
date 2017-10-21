	@Override
	public void registerChild(ServiceRegistryImplementor child) {
		if ( childRegistries == null ) {
			childRegistries = new HashSet<ServiceRegistryImplementor>();
		}
		if ( !childRegistries.add( child ) ) {
			LOG.warnf(
					"Child ServiceRegistry [%s] was already registered; this will end badly later...",
					child
			);
		}
	}
