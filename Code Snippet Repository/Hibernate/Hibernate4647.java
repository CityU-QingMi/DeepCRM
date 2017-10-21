	private <R extends Service> R initializeService(ServiceBinding<R> serviceBinding) {
		if ( log.isTraceEnabled() ) {
			log.tracev( "Initializing service [role={0}]", serviceBinding.getServiceRole().getName() );
		}

		// PHASE 1 : create service
		R service = createService( serviceBinding );
		if ( service == null ) {
			return null;
		}

		// PHASE 2 : inject service (***potentially recursive***)
		serviceBinding.getLifecycleOwner().injectDependencies( serviceBinding );

		// PHASE 3 : configure service
		serviceBinding.getLifecycleOwner().configureService( serviceBinding );

		// PHASE 4 : Start service
		serviceBinding.getLifecycleOwner().startService( serviceBinding );

		return service;
	}
