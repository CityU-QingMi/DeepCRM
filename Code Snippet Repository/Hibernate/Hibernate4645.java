	@SuppressWarnings({ "" })
	protected <R extends Service> ServiceBinding<R> locateServiceBinding(Class<R> serviceRole, boolean checkParent) {
		ServiceBinding<R> serviceBinding = serviceBindingMap.get( serviceRole );
		if ( serviceBinding == null && checkParent && parent != null ) {
			// look in parent
			serviceBinding = parent.locateServiceBinding( serviceRole );
		}

		if ( serviceBinding != null ) {
			return serviceBinding;
		}

		if ( !allowCrawling ) {
			return null;
		}

		// look for a previously resolved alternate registration
		final Class alternative = roleXref.get( serviceRole );
		if ( alternative != null ) {
			return serviceBindingMap.get( alternative );
		}

		// perform a crawl looking for an alternate registration
		for ( ServiceBinding binding : serviceBindingMap.values() ) {
			if ( serviceRole.isAssignableFrom( binding.getServiceRole() ) ) {
				// we found an alternate...
				log.alternateServiceRole( serviceRole.getName(), binding.getServiceRole().getName() );
				registerAlternate( serviceRole, binding.getServiceRole() );
				return binding;
			}

			if ( binding.getService() != null && serviceRole.isInstance( binding.getService() ) ) {
				// we found an alternate...
				log.alternateServiceRole( serviceRole.getName(), binding.getServiceRole().getName() );
				registerAlternate( serviceRole, binding.getServiceRole() );
				return binding;
			}
		}

		return null;
	}
