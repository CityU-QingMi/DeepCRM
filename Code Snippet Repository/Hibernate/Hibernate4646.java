	@Override
	public <R extends Service> R getService(Class<R> serviceRole) {
		// TODO: should an exception be thrown if active == false???
		R service = serviceRole.cast( initializedServiceByRole.get( serviceRole ) );
		if ( service != null ) {
			return service;
		}

		//Any service initialization needs synchronization
		synchronized ( this ) {
			// Check again after having acquired the lock:
			service = serviceRole.cast( initializedServiceByRole.get( serviceRole ) );
			if ( service != null ) {
				return service;
			}

			final ServiceBinding<R> serviceBinding = locateServiceBinding( serviceRole );
			if ( serviceBinding == null ) {
				throw new UnknownServiceException( serviceRole );
			}
			service = serviceBinding.getService();
			if ( service == null ) {
				service = initializeService( serviceBinding );
			}
			if ( service != null ) {
				// add the service only after it is completely initialized
				initializedServiceByRole.put( serviceRole, service );
			}
			return service;
		}
	}
