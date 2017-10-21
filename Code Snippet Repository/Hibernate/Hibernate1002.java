	@Override
	public void destroy() {
		if ( !active ) {
			return;
		}
		active = false;
		destroy( classLoaderServiceBinding );
		destroy( strategySelectorBinding );
		destroy( integratorServiceBinding );

		if ( childRegistries != null ) {
			for(ServiceRegistry serviceRegistry : childRegistries) {
				if(serviceRegistry instanceof ServiceRegistryImplementor) {
					ServiceRegistryImplementor serviceRegistryImplementor = (ServiceRegistryImplementor) serviceRegistry;
					serviceRegistryImplementor.destroy();
				}
			}
		}
	}
